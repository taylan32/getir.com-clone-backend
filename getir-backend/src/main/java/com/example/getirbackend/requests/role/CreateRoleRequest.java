package com.example.getirbackend.requests.role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateRoleRequest {

	@NotBlank(message ="Rol adı zorunlu")
	@Size(min  =2, max = 20, message = "Rol adı en az 2 en çok 20 karakter içerebilir.")
	private String name;
	
}
