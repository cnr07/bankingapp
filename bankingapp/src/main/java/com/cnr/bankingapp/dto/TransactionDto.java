package com.cnr.bankingapp.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class TransactionDto {
	
	private String username;
	private BigDecimal amount;
	private UUID from;
	private UUID to;
}
