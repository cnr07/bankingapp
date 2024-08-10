package com.cnr.bankingapp.service;

import java.util.List;
import java.util.UUID;

import com.cnr.bankingapp.dto.AccountDto;
import com.cnr.bankingapp.dto.SearchAccountDto;
import com.cnr.bankingapp.dto.UpdateAccountDto;
import com.cnr.bankingapp.entity.Account;

public interface AccountService {
	
	Account createAccount(AccountDto account);
	
	List<Account> searchAccounts(SearchAccountDto searchAccount);
	
	Account updateAccount(UpdateAccountDto updateAccount);
	
	int deleteAccount (UUID id);
	
	Account detailsAccount (UUID id);

}
