package com.example.getirbackend.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.getirbackend.requests.userRole.CreateUserRoleRequest;
import com.example.getirbackend.responses.userRole.CreatedUserRoleResponse;
import com.example.getirbackend.responses.userRole.GetListUserRoleResponse;
import com.example.getirbackend.services.userRole.UserRoleService;

@RestController
@RequestMapping("/api/	userroles")
public class UserRolesController {

	@Autowired
	private UserRoleService userRoleService;

	@PostMapping("/")
	public ResponseEntity<CreatedUserRoleResponse> add(@RequestBody CreateUserRoleRequest request) throws Exception {
		return ResponseEntity
				.created(URI.create(
						ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/userroles").toUriString()))
				.body(this.userRoleService.add(request));
	}
	
	@GetMapping("/getUserRoles")
	public ResponseEntity<List<GetListUserRoleResponse>> getAll(@RequestParam long userId) {
		return ResponseEntity.ok(this.userRoleService.getAll(userId));
	}

}
