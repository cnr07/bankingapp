package com.cnr.bankingapp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cnr.bankingapp.entity.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
	
	Optional<User> findByUsername(String username);

}
