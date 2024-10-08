package com.cnr.bankingapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnr.bankingapp.dto.UserDto;
import com.cnr.bankingapp.entity.User;
import com.cnr.bankingapp.repository.UserRepository;
import com.cnr.bankingapp.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	//Constructor dependency injection
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	

	

	
	
	
	
	
	
	
	

}
