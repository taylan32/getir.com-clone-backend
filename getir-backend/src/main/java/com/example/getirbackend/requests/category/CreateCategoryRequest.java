package com.example.getirbackend.requests.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateCategoryRequest {

	@NotBlank(message = "Kategori adı boş olamaz")
	@Size(min = 2, max = 20, message = "Kategori adı en az 2 en çok 20 karakter içerebilir.")
	private String name;

}
