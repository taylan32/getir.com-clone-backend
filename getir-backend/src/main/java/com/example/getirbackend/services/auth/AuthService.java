package com.example.getirbackend.services.auth;

import com.example.getirbackend.requests.auth.LoginRequest;
import com.example.getirbackend.requests.auth.RegisterRequest;
import com.example.getirbackend.responses.auth.LoginResponse;
import com.example.getirbackend.responses.auth.RegisterResponse;

public interface AuthService {

	RegisterResponse register(RegisterRequest request) throws Exception ;
	
	LoginResponse login(LoginRequest request ) throws Exception; 
	
}
