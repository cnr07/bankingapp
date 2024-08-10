package com.cnr.bankingapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cnr.bankingapp.entity.Account;
import com.cnr.bankingapp.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	Optional<List<Transaction>> findAllByFrom(Account account);
	
	Optional<List<Transaction>> findAllByTo(Account account);

}
