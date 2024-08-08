package com.cnr.bankingapp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cnr.bankingapp.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID>{

}
