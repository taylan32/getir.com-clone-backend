package com.example.getirbackend.responses.auth;

import lombok.Data;

@Data
public class LoginResponse {

	private long id;
	private String email;
	private String accessToken;
	//private String refreshToken;
	
}
