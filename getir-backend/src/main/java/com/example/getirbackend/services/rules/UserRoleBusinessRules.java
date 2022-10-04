package com.example.getirbackend.services.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.getirbackend.exceptions.BusinessException;
import com.example.getirbackend.exceptions.NotFoundException;
import com.example.getirbackend.repositories.RoleRepository;
import com.example.getirbackend.repositories.UserRepository;
import com.example.getirbackend.repositories.UserRoleRepository;

@Service
public class UserRoleBusinessRules {

	private UserRoleRepository userRoleRepository;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	@Autowired
	public UserRoleBusinessRules(UserRoleRepository userRoleRepository, UserRepository userRepository, RoleRepository roleRepository) {
		this.userRoleRepository = userRoleRepository;
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
	}
	
	public void userShouldExist(long userId) throws Exception {
		if(!this.userRepository.existsById(userId)) {
			throw new NotFoundException("Kullanıcı bulunamadı");
		}
	}
	
	public void roleShouldExist(long userId) throws Exception {
		if(!this.roleRepository.existsById(userId)) {
			throw new NotFoundException("Rol bulunamadı");
		}
	}
	
	public void roleCanNotDuplicate(long userId, long roleId) throws Exception{
		if(this.userRoleRepository.existsByUserIdAndRoleId(userId, roleId)) {
			throw new BusinessException("Bu rol kullanıcıya daha önce atanmış");
		}
	}
	
}
