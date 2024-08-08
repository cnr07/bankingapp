package com.cnr.bankingapp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="account", schema="banking-app")
@Data
public class Account {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID) 
    private UUID id;
	private String number;
	private String name;
	private BigDecimal balance;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}