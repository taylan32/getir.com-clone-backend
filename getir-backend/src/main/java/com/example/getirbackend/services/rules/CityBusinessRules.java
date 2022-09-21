package com.example.getirbackend.services.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.getirbackend.exceptions.BusinessException;
import com.example.getirbackend.exceptions.NotFoundException;
import com.example.getirbackend.repositories.CityRepository;

@Service
public class CityBusinessRules {

	@Autowired
	private CityRepository cityRepository;
	
	public void cityNameCanNotDuplicate(String name) throws Exception {
		if(this.cityRepository.existsByName(name)) {
			throw new BusinessException("Şehir adı daha önce eklenmiş");
		}
	}
	
	public void cityShouldExist(long id) throws Exception {
		if(!this.cityRepository.existsById(id)) {
			throw new NotFoundException("Şehir bulunamadı");
		}
	}
	
}
