package com.example.getirbackend.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundError extends ApiError{

	public NotFoundError(HttpStatus status, String type, String title, String detail) {
		super(status, type, title, detail);
	}

}
