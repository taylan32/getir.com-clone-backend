package com.example.getirbackend.api.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.getirbackend.requests.auth.LoginRequest;
import com.example.getirbackend.requests.auth.RegisterRequest;
import com.example.getirbackend.responses.auth.LoginResponse;
import com.example.getirbackend.responses.auth.RegisterResponse;
import com.example.getirbackend.services.auth.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) throws Exception {
		return ResponseEntity
				.created(URI
						.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth").toUriString()))
				.body(this.authService.register(request));
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request ) throws Exception {
		return ResponseEntity.ok(this.authService.login(request));
	}
	

}
