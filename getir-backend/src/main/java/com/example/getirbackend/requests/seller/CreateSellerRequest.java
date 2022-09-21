package com.example.getirbackend.requests.seller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateSellerRequest {
	
	@NotBlank(message = "Satıcı adı boş olamaz")
	@Size(min = 2, max = 20, message = "Satıcı adı en az 2 en çok 20 karakter olabilir.")
	private String name;
	
	@NotBlank(message = "Telefon numarası boş olamaz")
	private String phoneNumber;
	
	@NotBlank(message = "Adres boş olamaz")
	private String address;

	private long cityId;
}
