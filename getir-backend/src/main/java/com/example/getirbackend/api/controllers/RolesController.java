package com.example.getirbackend.api.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.getirbackend.requests.role.CreateRoleRequest;
import com.example.getirbackend.responses.role.CreatedRoleResponse;
import com.example.getirbackend.responses.role.GetListRoleResponse;
import com.example.getirbackend.services.role.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

	@Autowired
	private RoleService roleService;

	@PostMapping("/")
	public ResponseEntity<CreatedRoleResponse> add(@RequestBody @Valid CreateRoleRequest request) throws Exception {
		
		return ResponseEntity
				.created(URI
						.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles").toUriString()))
				.body(this.roleService.add(request));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<GetListRoleResponse>> getAll() {
		return ResponseEntity.ok(this.roleService.getAll());
	}

}
