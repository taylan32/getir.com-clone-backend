package com.example.getirbackend.services.userRole;

import java.util.List;

import com.example.getirbackend.requests.userRole.CreateUserRoleRequest;
import com.example.getirbackend.responses.userRole.CreatedUserRoleResponse;
import com.example.getirbackend.responses.userRole.GetListUserRoleResponse;

public interface UserRoleService {

	CreatedUserRoleResponse add(CreateUserRoleRequest request) throws Exception;
	List<GetListUserRoleResponse> getAll(long userId);
	
}
