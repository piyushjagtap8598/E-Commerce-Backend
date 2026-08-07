package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.LoginRequest;
import com.ecommerce.entity.User;
import com.ecommerce.security.JwtUtil;
import com.ecommerce.service.UserService;
import com.ecommerce.dto.LoginResponse;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController 
{
	@Autowired
	private UserService userservice;
	@Autowired
	private JwtUtil jwtutil;
	@PostMapping("/register")
	public ResponseEntity<User>registerUser(@RequestBody User user)
	{
		return ResponseEntity.ok(userservice.registerUser(user));
	}
	@PostMapping("/login")
	public ResponseEntity<?>loginUser(@RequestBody LoginRequest request)
	{
		 User user= userservice.authenticateUser(request.getEmail(), request.getPassword());
		 if(user == null)
		 {
			 return ResponseEntity.badRequest().body("Invalid Email Or Password");
		 }
		 String token=jwtutil.generateToken(user.getEmail());
		 return ResponseEntity.ok(new LoginResponse(token,user.getName(),user.getEmail(),user.getRole()));
	}

}
