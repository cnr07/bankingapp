package com.cnr.bankingapp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cnr.bankingapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

}
