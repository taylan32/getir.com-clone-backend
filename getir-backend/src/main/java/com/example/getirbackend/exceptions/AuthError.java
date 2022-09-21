package com.example.getirbackend.exceptions;

import org.springframework.http.HttpStatus;

public class AuthError extends ApiError {

	public AuthError(HttpStatus status, String type, String title, String detail) {
		super(status, type, title, detail);
		// TODO Auto-generated constructor stub
	}

}
