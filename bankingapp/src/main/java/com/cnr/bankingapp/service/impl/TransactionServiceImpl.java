package com.cnr.bankingapp.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnr.bankingapp.constant.StatusType;
import com.cnr.bankingapp.dto.TransactionDto;
import com.cnr.bankingapp.entity.Account;
import com.cnr.bankingapp.entity.Transaction;
import com.cnr.bankingapp.exception.BankingException;
import com.cnr.bankingapp.repository.AccountRepository;
import com.cnr.bankingapp.repository.TransactionRepository;
import com.cnr.bankingapp.repository.UserRepository;
import com.cnr.bankingapp.security.JwtService;
import com.cnr.bankingapp.service.TransactionService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	
	private AccountRepository accountRepository;
	private UserRepository userRepository;
	private TransactionRepository transactionRepository;
	private JwtService jwtService;
	
	//Constructor dependency injection
	@Autowired
	TransactionServiceImpl(AccountRepository accountRepository,UserRepository userRepository,TransactionRepository transactionRepository,JwtService jwtService){
		this.accountRepository = accountRepository;
		this.userRepository = userRepository;
		this.transactionRepository = transactionRepository;
		this.jwtService = jwtService;
	}

	@Override
	public Transaction transferMoney(TransactionDto transaction,String jwtt) {
		try {
			String jwt = jwtt.substring(7);
			
			Optional<Account> fromAccount = accountRepository.findById(transaction.getFrom());
			if(!fromAccount.isPresent()) {
				throw new BankingException("From account could not find!");
			}
			
			Optional<Account> toAccount = accountRepository.findById(transaction.getTo());
			if(!toAccount.isPresent()) {
				throw new BankingException("To account could not find!");
			}
			
			Transaction newTransaction = Transaction.builder().amount(transaction.getAmount())
					.status(StatusType.FAILED)
					.transactionDate(LocalDateTime.now())
					.from(fromAccount.get())
					.to(toAccount.get())
					.build();
			
			if(!jwtService.extractUsername(jwt).equals(transaction.getUsername())) {
				transactionRepository.save(newTransaction);
				return newTransaction;
			}
			
			if(!fromAccount.get().getUser().getUsername().equals(transaction.getUsername())) {
				transactionRepository.save(newTransaction);
				return newTransaction;
			}
			
			BigDecimal balance = fromAccount.get().getBalance();
			BigDecimal amount = transaction.getAmount();
			balance = balance.subtract(amount);
			if(balance.signum()<0) {
				transactionRepository.save(newTransaction);
				return newTransaction;
			}
			
			newTransaction.setStatus(StatusType.SUCCESS);
			transactionRepository.save(newTransaction);
			return newTransaction;
		}
		catch(Exception e) {
			throw new BankingException("Error on transfer money!");
		}
		
		
		
	}

	@Override
	public List<Transaction> transactionHistory(UUID id,String jwtt) {
		
		try {
			String jwt = jwtt.substring(7);

			Optional<Account> account = accountRepository.findById(id);
			if(!account.isPresent()) {
				throw new BankingException("Accont is not found!");
			}
			
			if(!jwtService.extractUsername(jwt).equals(account.get().getUser().getUsername())) {
				throw new BankingException("User not auth on account to see history!");
			}
			
			Optional<List<Transaction>> transactionsFrom = transactionRepository.findAllByFrom(account.get());
			Optional<List<Transaction>> transactionsTo = transactionRepository.findAllByTo(account.get());
			
			List<Transaction> transactions = new ArrayList<>();
			transactions.addAll(transactionsFrom.get());
			transactions.addAll(transactionsTo.get());
			
			return transactions;
		}catch(Exception e) {
			throw new BankingException("Error on transaction history!");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
