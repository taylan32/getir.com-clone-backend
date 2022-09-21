package com.example.getirbackend.requests.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginRequest {

	@NotBlank(message = "Email boş olamaz")
	private String email;
	
	@NotBlank(message = "Sifre boş olamaz")
	@Size(min = 4, max = 20, message = "Geçersiz şifre")
	private String password;
	
}
