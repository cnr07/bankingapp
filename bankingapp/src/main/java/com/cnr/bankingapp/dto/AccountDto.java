package com.cnr.bankingapp.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccountDto {
	
	private String name;
	private String username;
	private String email;
	private BigDecimal balance;

}
