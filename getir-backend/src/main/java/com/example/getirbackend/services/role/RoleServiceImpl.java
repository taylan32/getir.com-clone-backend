package com.example.getirbackend.services.role;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.getirbackend.models.Role;
import com.example.getirbackend.repositories.RoleRepository;
import com.example.getirbackend.requests.role.CreateRoleRequest;
import com.example.getirbackend.responses.role.CreatedRoleResponse;
import com.example.getirbackend.responses.role.GetListRoleResponse;
import com.example.getirbackend.services.rules.RoleBusinessRules;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;
	private ModelMapper mapper;
	private RoleBusinessRules roleBusinessRules;

	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository, ModelMapper mapper, RoleBusinessRules roleBusinessRules) {
		this.roleRepository = roleRepository;
		this.mapper = mapper;
		this.roleBusinessRules = roleBusinessRules;
	}

	@Override
	public CreatedRoleResponse add(CreateRoleRequest request) throws Exception {
		this.roleBusinessRules.roleNameCanNotDuplicate(request.getName());

		Role mappedRole = this.mapper.map(request, Role.class);
		Role createdRole = this.roleRepository.save(mappedRole);
		CreatedRoleResponse response = this.mapper.map(createdRole, CreatedRoleResponse.class);

		return response;
	}

	@Override
	public List<GetListRoleResponse> getAll() {
		List<Role> roles = this.roleRepository.findAll();
		List<GetListRoleResponse> roleResponse = new ArrayList<>();
		roles.forEach(role -> {
			GetListRoleResponse response = this.mapper.map(role, GetListRoleResponse.class);
			roleResponse.add(response);
		});
		return roleResponse;
	}

}
