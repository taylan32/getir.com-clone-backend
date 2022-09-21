package com.example.getirbackend.responses.seller;

import com.example.getirbackend.models.City;

import lombok.Data;

@Data
public class GetByIdSellerResponse {

	private long id;
	private String name;
	private String address;
	private String phoneNumber;
	private double rate;
	private City city;
	
}
