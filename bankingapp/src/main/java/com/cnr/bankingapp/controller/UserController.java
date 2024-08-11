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

import com.cnr.bankingapp.dto.AuthRequestDto;
import com.cnr.bankingapp.dto.AuthResponseDto;
import com.cnr.bankingapp.dto.RefreshTokenRequestDto;
import com.cnr.bankingapp.dto.UserDto;
import com.cnr.bankingapp.entity.User;
import com.cnr.bankingapp.security.AuthenticationService;
import com.cnr.bankingapp.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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
	@Operation(summary = "Returns tokens",
    description = "Takes object that contains users username(username) users password(password) users email(email) users role(role) -> role can be"
    		+ "ADMIN or USER. "
    
			)
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "If user successfully registered than, returns access_token refresh_token and message"
            		+ "otherwise refresh_token and access_token would be null!"),
    })
    public ResponseEntity<AuthResponseDto> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Returns tokens",
    description = "Takes object that contains users username(username) users password(password) "
    
			)
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "If user successfully logged than, returns access_token refresh_token and message"),
            @ApiResponse(responseCode = "401", description = "Error on login")
	})
    public ResponseEntity<AuthResponseDto> login(@RequestBody User request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/refresh_token")
    @Operation(summary = "Returns tokens",
    description = "Takes object that contains auth header authHeader(Bearer ***) "
    
			)
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "If user successfully logged than, returns access_token refresh_token and message"),
            @ApiResponse(responseCode = "401", description = "Error on login")
	})
    public ResponseEntity refreshToken(@RequestBody RefreshTokenRequestDto request) {
        return authService.refreshToken(request);
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
