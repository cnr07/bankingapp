package com.cnr.bankingapp.service;

import java.util.List;
import java.util.UUID;

import com.cnr.bankingapp.dto.TransactionDto;
import com.cnr.bankingapp.entity.Transaction;

public interface TransactionService {
	
	Transaction transferMoney(TransactionDto transaction,String jwt);
	
	List<Transaction> transactionHistory(UUID id,String jwt);

}
