package com.example.getirbackend.requests.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterRequest {

	@NotBlank(message = "Email boş olamaz")
	@Email
	private String email;
	
	@NotBlank(message = "Şifre boş olamaz")
	@Size(min = 4, max = 20, message = "Şifre en az 4 en çok 20 karakter olabilir.")
	private String password;
	
}
