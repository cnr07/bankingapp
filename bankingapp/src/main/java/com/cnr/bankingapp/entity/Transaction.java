package com.cnr.bankingapp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.cnr.bankingapp.constant.RoleType;
import com.cnr.bankingapp.constant.StatusType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="transaction", schema="banking-app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	@ManyToOne
	@JoinColumn
	private Account from;
	@ManyToOne
	@JoinColumn
	private Account to;
	private BigDecimal amount;
	private LocalDateTime transactionDate;
	@Enumerated(EnumType.STRING)
	private StatusType status;
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}