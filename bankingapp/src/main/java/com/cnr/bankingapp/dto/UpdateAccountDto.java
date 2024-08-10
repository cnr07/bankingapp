package com.cnr.bankingapp.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class UpdateAccountDto {
	private String username;
	private String number;
	private BigDecimal balance;

}
