package com.example.getirbackend.requests.city;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateCityRequest {
	
	@NotBlank(message = "Şehir adı boş olamaz")
	@Size(min = 3, max = 14, message = "Şehir adı en az 3 en çok 14 karakter olabilir.")
	private String name;

}
