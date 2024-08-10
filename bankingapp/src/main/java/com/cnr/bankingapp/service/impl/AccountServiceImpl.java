package com.cnr.bankingapp.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnr.bankingapp.dto.AccountDto;
import com.cnr.bankingapp.dto.SearchAccountDto;
import com.cnr.bankingapp.dto.UpdateAccountDto;
import com.cnr.bankingapp.entity.Account;
import com.cnr.bankingapp.entity.User;
import com.cnr.bankingapp.exception.BankingException;
import com.cnr.bankingapp.repository.AccountRepository;
import com.cnr.bankingapp.repository.UserRepository;
import com.cnr.bankingapp.security.JwtService;
import com.cnr.bankingapp.service.AccountService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	
	private AccountRepository accountRepository;
	private UserRepository userRepository;
	private JwtService jwtService;
	
	//Constructor dependency injection
	@Autowired
	AccountServiceImpl(AccountRepository accountRepository,UserRepository userRepository,JwtService jwtService){
		this.accountRepository = accountRepository;
		this.userRepository = userRepository;
		this.jwtService = jwtService;
	}

	@Override
	public Account createAccount(AccountDto account,String jwtt) {
		
		try {
			String jwt = jwtt.substring(7);
			if(!jwtService.extractUsername(jwt).equals(account.getUsername())) {
				throw new BankingException("User does not have auth account!");
			}
			User user = userRepository.findByUsername(account.getUsername()).get();
			Optional<List<Account>> accountList = accountRepository.findAllByUser(user);
			if(accountList.isPresent()) {
				List<Account> accountSame = accountList.get().stream().filter(x->x.getName().equals(account.getName())).collect(Collectors.toList());
				if(accountSame.size()>0) {throw new BankingException("Account name already saved!");}
			}
			Account accountToSave = Account.builder()
					.name(account.getName())
					.balance(account.getBalance())
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.build();
			Random rand = new Random();
			while(true) {
				int accountNumber=rand.nextInt(9999999 - 1000000 + 1) + 1000000;
				if(!accountRepository.findByNumber("TR"+accountNumber).isPresent()) {
					accountToSave.setNumber("TR"+accountNumber);
					break;
				}
			}
			
			accountToSave.setUser(user);
			accountRepository.save(accountToSave);
			return accountToSave;
		}catch(Exception e) {
			throw new BankingException("Error on creating account!");
		}
		
	}

	@Override
	public List<Account> searchAccounts(SearchAccountDto searchAccount,String jwtt) {
		try {
			String jwt = jwtt.substring(7);
			if(!jwtService.extractUsername(jwt).equals(searchAccount.getUsername())) {
				throw new BankingException("User does not have auth account!");
			}
			User user = userRepository.findByUsername(searchAccount.getUsername()).get();
			Optional<List<Account>> accounts = accountRepository.findAllByUser(user);
			List<Account> response = new ArrayList<Account>();
			if(accounts.isPresent()) {
				if(searchAccount.getName()!=null ) {
					response = accounts.get().stream().filter(x->x.getName().equals(searchAccount.getName())).collect(Collectors.toList());
				}
				else if(searchAccount.getNumber()!=null ) {
					response = accounts.get().stream().filter(x->x.getNumber().equals(searchAccount.getNumber())).collect(Collectors.toList());
				}
				else {
					response = accounts.get();
				}
				return response;
			}else {
				throw new BankingException("User does not have any account!");
			}
		}catch(Exception e) {
			throw new BankingException("Error on searching accounts!");
		}
		
		
	}

	@Override
	public Account updateAccount(UpdateAccountDto updateAccount,String jwtt) {
		try {
			String jwt = jwtt.substring(7);
			if(!jwtService.extractUsername(jwt).equals(updateAccount.getUsername())) {
				throw new BankingException("User does not have auth account!");
			}
			User user = userRepository.findByUsername(updateAccount.getUsername()).get();
			Optional<Account> accountToUpdate = accountRepository.findByNumber(updateAccount.getNumber());
			if(!accountToUpdate.isPresent()) {throw new BankingException("Account does not found!");}
			BigDecimal accountBalance = accountToUpdate.get().getBalance();
			BigDecimal updateBalance = updateAccount.getBalance();
			accountBalance=accountBalance.add(updateBalance);
			accountToUpdate.get().setBalance(accountBalance);
			accountToUpdate.get().setUpdatedAt(LocalDateTime.now());
			accountRepository.save(accountToUpdate.get());
			return accountToUpdate.get();
		}catch(Exception e) {
			throw new BankingException("Error on updating account!");
		}
		
	}

	@Override
	public int deleteAccount(UUID id,String jwtt) {
		try {
			String jwt = jwtt.substring(7);
			Optional<Account> accountToDel = accountRepository.findById(id);
			if(!accountToDel.isPresent()) {throw new BankingException("Account does not found!");}
			if(!jwtService.extractUsername(jwt).equals(accountToDel.get().getUser().getUsername())) {
				throw new BankingException("User does not have auth account!");
			}
			accountRepository.delete(accountToDel.get());
			return 0;
		}catch(Exception e) {
			throw new BankingException("Error on deleting account!");
		}
		
	}

	@Override
	public Account detailsAccount(UUID id,String jwtt) {
		try {
			String jwt = jwtt.substring(7);
			Optional<Account> account = accountRepository.findById(id);
			if(!account.isPresent()) {throw new BankingException("Account does not found!");}
			if(!jwtService.extractUsername(jwt).equals(account.get().getUser().getUsername())) {
				throw new BankingException("User does not have auth account!");
			}
			return account.get();
		}catch(Exception e) {
			throw new BankingException("Error on getting details account!");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
