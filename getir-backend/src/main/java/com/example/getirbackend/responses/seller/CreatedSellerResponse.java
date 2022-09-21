package com.example.getirbackend.responses.seller;

import lombok.Data;

@Data
public class CreatedSellerResponse {

	private long id;
	private String name;
	private String phoneNumber;
	private String address;
	private long cityId;
	
}
