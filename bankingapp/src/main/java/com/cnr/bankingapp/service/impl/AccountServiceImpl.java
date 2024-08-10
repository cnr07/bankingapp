package com.cnr.bankingapp.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnr.bankingapp.dto.AccountDto;
import com.cnr.bankingapp.entity.Account;
import com.cnr.bankingapp.entity.User;
import com.cnr.bankingapp.exception.BankingException;
import com.cnr.bankingapp.repository.AccountRepository;
import com.cnr.bankingapp.repository.UserRepository;
import com.cnr.bankingapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	private AccountRepository accountRepository;
	private UserRepository userRepository;
	
	//Constructor dependency injection
	@Autowired
	AccountServiceImpl(AccountRepository accountRepository,UserRepository userRepository){
		this.accountRepository = accountRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Account createAccount(AccountDto account) {
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
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
