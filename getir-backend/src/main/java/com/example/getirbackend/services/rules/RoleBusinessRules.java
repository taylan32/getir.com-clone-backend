package com.example.getirbackend.services.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.getirbackend.exceptions.BusinessException;
import com.example.getirbackend.repositories.RoleRepository;

@Service
public class RoleBusinessRules {

	@Autowired
	private RoleRepository roleRepository;
	
	
	public void roleNameCanNotDuplicate(String name) throws Exception{
		if(this.roleRepository.existsByName(name)) {
			throw new BusinessException("Bu rol daha önce eklenmiş");
		}
	}
}
