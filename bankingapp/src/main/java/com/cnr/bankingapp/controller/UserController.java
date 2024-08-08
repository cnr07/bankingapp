package com.cnr.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnr.bankingapp.entity.User;
import com.cnr.bankingapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	
	//Constructor dependency injection
	@Autowired
	UserController(UserService userService){
		this.userService = userService;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<Void> createUser(@RequestBody User newUser) {
		User user = userService.saveUser(newUser);
		if(user != null) 
			return new ResponseEntity<>(HttpStatus.CREATED);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
	
	
	

}
