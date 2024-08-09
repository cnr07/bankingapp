package com.cnr.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;

import com.cnr.bankingapp.dto.AuthRequestDTO;
import com.cnr.bankingapp.dto.AuthResponseDto;
import com.cnr.bankingapp.dto.UserDto;
import com.cnr.bankingapp.entity.User;
import com.cnr.bankingapp.security.AuthenticationService;
import com.cnr.bankingapp.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private UserService userService;
	private final AuthenticationService authService;
	
	//Constructor dependency injection
	@Autowired
	UserController(UserService userService,AuthenticationService authService){
		this.userService = userService;
		this.authService = authService;
	}
	
	
	@PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody User request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/refresh_token")
    public ResponseEntity refreshToken(HttpServletRequest request,HttpServletResponse response) {
        return authService.refreshToken(request, response);
    }
	
	
	
	
	
    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from secured url");
    }

    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Hello from admin only url");
    }
	
	
	
	
	
	

}
