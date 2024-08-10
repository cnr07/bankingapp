package com.cnr.bankingapp.service;

import com.cnr.bankingapp.dto.AccountDto;
import com.cnr.bankingapp.entity.Account;

public interface AccountService {
	
	Account createAccount(AccountDto account);

}
