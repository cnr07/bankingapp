package com.cnr.bankingapp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.cnr.bankingapp.constant.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Account {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID) 
    private UUID id;
	private String number;
	private String name;
	private BigDecimal balance;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	@ManyToOne
    @JoinColumn(name = "user_id")
	@JsonIgnore
    private User user;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}