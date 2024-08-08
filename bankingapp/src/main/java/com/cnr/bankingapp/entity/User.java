package com.cnr.bankingapp.entity;



import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user", schema="banking-app")
@Data
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID) 
    private UUID id;
	private String username;
	private String password;
	private String email;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
