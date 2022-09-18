package com.example.getirbackend.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessError extends ApiError{

	public BusinessError(HttpStatus status, String type, String title, String detail) {
		super(status, type, title, detail);
	}

}
