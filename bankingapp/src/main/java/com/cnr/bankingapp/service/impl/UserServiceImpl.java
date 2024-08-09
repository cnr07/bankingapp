package com.cnr.bankingapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnr.bankingapp.dto.UserDto;
import com.cnr.bankingapp.entity.User;
import com.cnr.bankingapp.repository.UserRepository;
import com.cnr.bankingapp.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	//Constructor dependency injection
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(UserDto user) {
		User userToSave = new User();
		userToSave.setUsername(user.getUsername());
		//userToSave.setPassword(passwordEncoder.encode(user.getPassword()));
		userToSave.setEmail(user.getEmail());
		User savedUser = userRepository.save(userToSave);
		return savedUser;
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
	
	

}
