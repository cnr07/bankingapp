package com.cnr.bankingapp.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchAccountDto {
	
	private String username;
	private String number;
	private String name;

}
