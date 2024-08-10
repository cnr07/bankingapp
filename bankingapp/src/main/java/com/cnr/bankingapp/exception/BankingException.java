package com.cnr.bankingapp.exception;

public class BankingException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public BankingException(String message){
		super(message);
	}

}
