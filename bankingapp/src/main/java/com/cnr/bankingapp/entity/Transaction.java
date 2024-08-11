package com.cnr.bankingapp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.cnr.bankingapp.constant.RoleType;
import com.cnr.bankingapp.constant.StatusType;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Transaction", description = "Transaction entity")
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Schema(name = "id", description = "Transaction id", example = "1L")
	private Long id;
	@ManyToOne
	@JoinColumn
	@Schema(name = "from", description = "Transaction from account")
	private Account from;
	@ManyToOne
	@JoinColumn
	@Schema(name = "to", description = "Transaction to account")
	private Account to;
	@Schema(name = "amount", description = "Transaction amount", example = "500.00")
	private BigDecimal amount;
	@Schema(name = "transactionDate", description = "Transaction date", example = "2024-08-11 01:30:41.219106")
	private LocalDateTime transactionDate;
	@Enumerated(EnumType.STRING)
	@Schema(name = "status", description = "Transaction status", example = "SUCCESS")
	private StatusType status;
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}