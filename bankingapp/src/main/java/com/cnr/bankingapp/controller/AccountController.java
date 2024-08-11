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
import org.springframework.web.bind.annotation.RequestHeader;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Account", description = "All account ops handled here...")
public class AccountController {
	
	private AccountService accountService;
	
	AccountController(AccountService accountService){
		this.accountService = accountService;
	}
	
	@PostMapping("/create")
	@Operation(summary = "Returns created account",
    description = "Takes object that contains accounts name (name), accounts balance(balance) users username(username) users email(email) "
    		+"Header should countain Authorization header with Bearer prefix!")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns created account"),
            @ApiResponse(responseCode = "400", description = "Error on creating account")
    })
    public ResponseEntity<Account> createAccount(@RequestBody AccountDto request,@RequestHeader(name = "Authorization", required = true) String jwt) {
		return ResponseEntity.ok(accountService.createAccount(request,jwt));
    }
	
	@PostMapping
	@Operation(summary = "Returns users accounts",
    description = "Takes object that contains accounts name (name), accounts number(number) users username(username)"
    		+ "account name and number are provided whether filter requested (optional)"
    		+"Header should countain Authorization header with Bearer prefix!")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns users accounts"),
            @ApiResponse(responseCode = "400", description = "Error on searching accounts")
    })
    public ResponseEntity<List<Account>> searchAccounts(@RequestBody SearchAccountDto request,@RequestHeader(name = "Authorization", required = true) String jwt) {
		return ResponseEntity.ok(accountService.searchAccounts(request,jwt));
    }
	
	@PutMapping
	@Operation(summary = "Returns updated account",
    description = "Takes object that contains accounts number(number) users username(username) balance that will be added to the accounts balance(balance)"
    		+ "balance can be minus(-) to subtract balance"
    		+"Header should countain Authorization header with Bearer prefix!")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns updated account"),
            @ApiResponse(responseCode = "400", description = "Error on updating account")
    })
    public ResponseEntity<Account> updateAccount(@RequestBody UpdateAccountDto request,@RequestHeader(name = "Authorization", required = true) String jwt) {
		return ResponseEntity.ok(accountService.updateAccount(request,jwt));
    }
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Returns 0 if account succesfully deleted",
    description = "Takes object that contains accounts id(id) "
    		+"Header should countain Authorization header with Bearer prefix!")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "0 Returns succesfully deleted account"),
            @ApiResponse(responseCode = "400", description = "Error on deleting account")
    })
    public ResponseEntity deleteAccount(@PathVariable UUID id,@RequestHeader(name = "Authorization", required = true) String jwt) {
		return ResponseEntity.ok(accountService.deleteAccount(id,jwt));
    }
	
	@GetMapping("/{id}")
	@Operation(summary = "Returns account",
    description = "Takes object that contains accounts id(id) "
    		+"Header should countain Authorization header with Bearer prefix!")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns account"),
            @ApiResponse(responseCode = "400", description = "Error on returning account")
    })
    public ResponseEntity<Account> detailsAccount(@PathVariable UUID id,@RequestHeader(name = "Authorization", required = true) String jwt) {
		return ResponseEntity.ok(accountService.detailsAccount(id,jwt));
    }
	
	@ExceptionHandler(BankingException.class)
	public ResponseEntity<ErrorResponseDto> handleException(BankingException bankingException){
		ErrorResponseDto response = new ErrorResponseDto(HttpStatus.BAD_REQUEST,bankingException.getMessage());
		return new ResponseEntity<>(response,response.getStatus());
	}
	
	
	
	
	
	
	
	
	
	
	

}
