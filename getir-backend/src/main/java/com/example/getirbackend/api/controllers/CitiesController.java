package com.example.getirbackend.api.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.getirbackend.requests.city.CreateCityRequest;
import com.example.getirbackend.requests.city.DeleteCityRequest;
import com.example.getirbackend.responses.city.CreatedCityResponse;
import com.example.getirbackend.responses.city.DeletedCityResponse;
import com.example.getirbackend.responses.city.GetListCityResponse;
import com.example.getirbackend.services.city.CityService;
import com.example.getirbackend.utils.pagination.BasePageableModel;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

	@Autowired
	private CityService cityService;

	@PostMapping("/")
	public ResponseEntity<CreatedCityResponse> add(@Valid @RequestBody CreateCityRequest request) throws Exception {
		return ResponseEntity
				.created(URI
						.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/cities").toUriString()))
				.body(this.cityService.add(request));
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<BasePageableModel<GetListCityResponse>> getAll(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
		return ResponseEntity.ok(this.cityService.getAll(pageNumber, pageSize));
	}
	
	@DeleteMapping("/")
	public ResponseEntity<DeletedCityResponse> delete(@RequestBody DeleteCityRequest request) throws Exception {
		return ResponseEntity.ok(this.cityService.delete(request));
	}
	

}
