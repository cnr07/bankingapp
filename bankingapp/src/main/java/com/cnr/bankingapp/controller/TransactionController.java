package com.cnr.bankingapp.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cnr.bankingapp.dto.AccountDto;
import com.cnr.bankingapp.dto.ErrorResponseDto;
import com.cnr.bankingapp.dto.TransactionDto;
import com.cnr.bankingapp.entity.Account;
import com.cnr.bankingapp.entity.Transaction;
import com.cnr.bankingapp.exception.BankingException;
import com.cnr.bankingapp.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transaction", description = "All transaction ops handled here...")
public class TransactionController {
	
	private TransactionService transactionService;
	
	
	@Autowired
	TransactionController(TransactionService transactionService){
		this.transactionService = transactionService;
	}
	
	
	@PostMapping("/transfer")
	@Operation(summary = "Returns transaction",
    description = "Takes object that contains accounts id(from) accounts id(to) amount that will be added to the accounts(to) balance(balance)"
    		+ "amount >=0"+"Header should countain Authorization header with Bearer prefix!"
			)
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns transaction that can be success or fail"),
            @ApiResponse(responseCode = "400", description = "Error on transaction")
    })
    public ResponseEntity<Transaction> transferMoney(@RequestBody TransactionDto request,@RequestHeader(name = "Authorization", required = true) String jwt) {
		return ResponseEntity.ok(transactionService.transferMoney(request,jwt));
    }
	
	@GetMapping("/account/{id}")
	@Operation(summary = "Returns accounts transactions",
    description = "Takes object that contains accounts id(id)"
    +"Header should countain Authorization header with Bearer prefix!"
			)
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns transactions that can be empty"),
            @ApiResponse(responseCode = "400", description = "Error on transaction history")
    })
    public ResponseEntity<List<Transaction>> transactionHistory(@PathVariable UUID id,@RequestHeader(name = "Authorization", required = true) String jwt) {
		return ResponseEntity.ok(transactionService.transactionHistory(id,jwt));
    }
	
	@ExceptionHandler(BankingException.class)
	public ResponseEntity<ErrorResponseDto> handleException(BankingException bankingException){
		ErrorResponseDto response = new ErrorResponseDto(HttpStatus.BAD_REQUEST,bankingException.getMessage());
		return new ResponseEntity<>(response,response.getStatus());
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
