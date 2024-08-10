package com.cnr.bankingapp.service;

import java.util.List;
import java.util.UUID;

import com.cnr.bankingapp.dto.AccountDto;
import com.cnr.bankingapp.dto.SearchAccountDto;
import com.cnr.bankingapp.dto.UpdateAccountDto;
import com.cnr.bankingapp.entity.Account;

public interface AccountService {
	
	Account createAccount(AccountDto account,String jwt);
	
	List<Account> searchAccounts(SearchAccountDto searchAccount,String jwt);
	
	Account updateAccount(UpdateAccountDto updateAccount,String jwt);
	
	int deleteAccount (UUID id,String jwt);
	
	Account detailsAccount (UUID id,String jwt);

}
