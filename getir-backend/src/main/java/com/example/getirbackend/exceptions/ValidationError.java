package com.example.getirbackend.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ValidationError extends ApiError {

	private Map<String, String> errors;

	public ValidationError(HttpStatus status, String type, String title, String detail, Map<String, String> errors) {
		super(status, type, title, detail);
		this.errors = errors;
	}

}
