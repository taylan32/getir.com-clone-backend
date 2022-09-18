package com.example.getirbackend.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiError {

	private HttpStatus status;
	private String type;
	private String title;
	private String detail;
	
	public ApiError(HttpStatus status, String type, String title, String detail) {
		this.status = status;
		this.type = type;
		this.title = title;
		this.detail = detail;
	}
	
	
}
