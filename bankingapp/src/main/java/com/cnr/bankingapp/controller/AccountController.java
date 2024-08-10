package com.cnr.bankingapp.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnr.bankingapp.dto.AccountDto;
import com.cnr.bankingapp.dto.AuthResponseDto;
import com.cnr.bankingapp.dto.ErrorResponseDto;
import com.cnr.bankingapp.dto.SearchAccountDto;
import com.cnr.bankingapp.dto.UpdateAccountDto;
import com.cnr.bankingapp.entity.Account;
import com.cnr.bankingapp.entity.User;
import com.cnr.bankingapp.exception.BankingException;
import com.cnr.bankingapp.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService;
	
	AccountController(AccountService accountService){
		this.accountService = accountService;
	}
	
	@PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDto request) {
		return ResponseEntity.ok(accountService.createAccount(request));
    }
	
	@PostMapping
    public ResponseEntity<List<Account>> searchAccounts(@RequestBody SearchAccountDto request) {
		return ResponseEntity.ok(accountService.searchAccounts(request));
    }
	
	@PutMapping
    public ResponseEntity<Account> updateAccount(@RequestBody UpdateAccountDto request) {
		return ResponseEntity.ok(accountService.updateAccount(request));
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity deleteAccount(@PathVariable UUID id) {
		return ResponseEntity.ok(accountService.deleteAccount(id));
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Account> detailsAccount(@PathVariable UUID id) {
		return ResponseEntity.ok(accountService.detailsAccount(id));
    }
	
	@ExceptionHandler(BankingException.class)
	public ResponseEntity<ErrorResponseDto> handleException(BankingException bankingException){
		ErrorResponseDto response = new ErrorResponseDto(HttpStatus.BAD_REQUEST,bankingException.getMessage());
		return new ResponseEntity<>(response,response.getStatus());
	}
	
	
	
	
	
	
	
	
	
	
	

}
