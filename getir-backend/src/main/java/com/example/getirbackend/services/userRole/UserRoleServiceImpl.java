package com.example.getirbackend.services.userRole;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.getirbackend.models.UserRole;
import com.example.getirbackend.repositories.UserRoleRepository;
import com.example.getirbackend.requests.userRole.CreateUserRoleRequest;
import com.example.getirbackend.responses.userRole.CreatedUserRoleResponse;
import com.example.getirbackend.responses.userRole.GetListUserRoleResponse;
import com.example.getirbackend.services.rules.UserRoleBusinessRules;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	private UserRoleRepository userRoleRepository;
	private ModelMapper mapper;
	private UserRoleBusinessRules userRoleBusinessRules;

	@Autowired
	public UserRoleServiceImpl(UserRoleRepository userRoleRepository, ModelMapper mapper,
			UserRoleBusinessRules userRoleBusinessRules) {
		this.userRoleRepository = userRoleRepository;
		this.mapper = mapper;
		this.userRoleBusinessRules = userRoleBusinessRules;
	}

	@Override
	public CreatedUserRoleResponse add(CreateUserRoleRequest request) throws Exception {
	
		this.userRoleBusinessRules.userShouldExist(request.getUserId());
		this.userRoleBusinessRules.roleShouldExist(request.getRoleId());
		
		UserRole mappedRole = this.mapper.map(request, UserRole.class);
		UserRole createdRole = this.userRoleRepository.save(mappedRole);
		CreatedUserRoleResponse response = this.mapper.map(createdRole, CreatedUserRoleResponse.class);
		
		return response;
	}

	@Override
	public List<GetListUserRoleResponse> getAll(long userId) {
		List<GetListUserRoleResponse> userRoles = this.userRoleRepository.getUserRoleWithDetail(userId);
		return userRoles;
	}

}
