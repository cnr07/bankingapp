package com.cnr.bankingapp.entity;



import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cnr.bankingapp.constant.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="user", schema="banking-app")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "User", description = "User entity")
public class User implements UserDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
	@Schema(name = "id", description = "User id", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
	
	@Schema(name = "username", description = "User username", example = "cnr")
	private String username;
	
	@Schema(name = "password", description = "User password", example = "password")
	private String password;
	
	@Schema(name = "email", description = "User email", example = "can@gmail.com")
	private String email;
	
	@Schema(name = "createdAt", description = "User created date", example = "2024-08-11 01:30:41.219106")
	private LocalDateTime createdAt;
	
	@Schema(name = "updatedAt", description = "User updated date", example = "2024-08-11 01:30:41.219106")
	private LocalDateTime updatedAt;
	
	@Enumerated(value = EnumType.STRING)
	@Schema(name = "role", description = "User role", example = "USER")
    private RoleType role;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	@Schema(name = "tokens", description = "User tokens")
    private List<Token> tokens;
	
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }



	
	
	
	
	
	
	
	
	
	
	
	
}