package com.example.getirbackend.services.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.getirbackend.exceptions.BusinessException;
import com.example.getirbackend.exceptions.NotFoundException;
import com.example.getirbackend.repositories.UserRepository;

@Service
public class AuthBusinessRules {

	private UserRepository userRepository;
	
	@Autowired
	public AuthBusinessRules(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void emailCanNotDuplicate(String email) throws Exception {
		if(this.userRepository.existsByEmail(email)) {
			throw new BusinessException("Email kullanımda.");
		}
	}
	
	public void userShouldExist(String email) throws Exception{
		if(!this.userRepository.existsByEmail(email)) {
			throw new NotFoundException("Email veya şifre yanlış");
		}
	}
	
}
