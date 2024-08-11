package com.cnr.bankingapp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.cnr.bankingapp.constant.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
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
@Table(name="account", schema="banking-app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Account", description = "Account entity")
public class Account {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID) 
	@Schema(name = "id", description = "Account id", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
	
	@Schema(name = "number", description = "Account number", example = "TR7520988")
	private String number;
	
	@Schema(name = "name", description = "Account name", example = "dolares1")
	private String name;
	
	@Schema(name = "balance", description = "Account balance", example = "11000.00")
	private BigDecimal balance;
	
	@Schema(name = "createdAt", description = "Account created date", example = "2024-08-11 01:30:41.219106")
	private LocalDateTime createdAt;
	
	@Schema(name = "updatedAt", description = "Account updated date", example = "2024-08-11 01:30:41.219106")
	private LocalDateTime updatedAt;
	@ManyToOne
    @JoinColumn(name = "user_id")
	@JsonIgnore
	@Schema(name = "user", description = "Account user")
    private User user;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}