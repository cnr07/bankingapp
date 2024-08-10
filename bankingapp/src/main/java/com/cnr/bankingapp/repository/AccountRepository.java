package com.cnr.bankingapp.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cnr.bankingapp.entity.Account;
import com.cnr.bankingapp.entity.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID>{
	
	Optional<Account> findByNumber(String number);
	
	Optional<List<Account>> findAllByUser(User user);

}
