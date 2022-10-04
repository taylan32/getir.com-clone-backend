package com.example.getirbackend.services.role;

import com.example.getirbackend.requests.role.CreateRoleRequest;
import com.example.getirbackend.responses.role.CreatedRoleResponse;
import com.example.getirbackend.responses.role.GetListRoleResponse;
import java.util.List;

public interface RoleService {
	
	CreatedRoleResponse add(CreateRoleRequest request) throws Exception;
	
	List<GetListRoleResponse> getAll();

}
