package com.example.getirbackend.api.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.getirbackend.requests.seller.CreateSellerRequest;
import com.example.getirbackend.requests.seller.UpdateSellerRequest;
import com.example.getirbackend.responses.seller.CreatedSellerResponse;
import com.example.getirbackend.responses.seller.GetByIdSellerResponse;
import com.example.getirbackend.responses.seller.GetListSellerResponse;
import com.example.getirbackend.responses.seller.UpdatedSellerResponse;
import com.example.getirbackend.services.seller.SellerService;
import com.example.getirbackend.utils.pagination.BasePageableModel;

@RestController
@RequestMapping("/api/sellers")
public class SellersController {

	@Autowired
	private SellerService sellerService;
	
	@PostMapping("/")
	public ResponseEntity<CreatedSellerResponse> add(@RequestBody @Valid CreateSellerRequest request) throws Exception {
		return ResponseEntity
				.created(URI.create(
						ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/sellers").toUriString()))
				.body(this.sellerService.add(request));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GetByIdSellerResponse> getById(@PathVariable long id) throws Exception {
		return ResponseEntity.ok(this.sellerService.getById(id));
	}
	
	@PatchMapping("/")
	public ResponseEntity<UpdatedSellerResponse> update(@RequestBody @Valid UpdateSellerRequest request) throws Exception {
		return ResponseEntity.ok(this.sellerService.update(request));
	}
	
	
	@GetMapping("/getAll")
	public ResponseEntity<BasePageableModel<GetListSellerResponse>> getAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
		return ResponseEntity.ok(this.sellerService.getAll(pageNumber, pageSize));
	}
	
}
