package com.example.getirbackend.services.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.getirbackend.exceptions.BusinessException;
import com.example.getirbackend.exceptions.NotFoundException;
import com.example.getirbackend.models.Seller;
import com.example.getirbackend.repositories.SellerRepository;

@Service
public class SellerBusinessRules {

	private SellerRepository sellerRepository;
	
	@Autowired
	public SellerBusinessRules(SellerRepository sellerRepository) {
		this.sellerRepository = sellerRepository;
	}
	
	public void sellerNameCanNotDuplicate(String name) throws Exception{
		if(this.sellerRepository.existsByName(name)) {
			throw new BusinessException("Satıcı zaten mevcut");
		}
	}
	
	public void sellerShouldExist(long id) throws Exception{
		if(!this.sellerRepository.existsById(id)) {
			throw new NotFoundException("Satıcı bulunamadı");
		}
	}
	
	
}
