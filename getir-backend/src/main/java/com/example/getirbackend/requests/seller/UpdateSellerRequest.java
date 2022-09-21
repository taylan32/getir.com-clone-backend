package com.example.getirbackend.requests.seller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateSellerRequest {

	private long id;
	
	@NotBlank(message = "Telefon numarası boş olamaz")
	private String phoneNumber;
	
	@NotBlank(message = "Adres boş olamaz")
	private String address;
	
}
