package com.example.getirbackend.api.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.getirbackend.requests.category.CreateCategoryRequest;
import com.example.getirbackend.requests.category.DeleteCategoryRequest;
import com.example.getirbackend.requests.category.UpdateCategoryRequest;
import com.example.getirbackend.responses.category.CreatedCategoryResponse;
import com.example.getirbackend.responses.category.DeletedCategoryResponse;
import com.example.getirbackend.responses.category.GetByIdCategoryResponse;
import com.example.getirbackend.responses.category.GetListCategoryResponse;
import com.example.getirbackend.responses.category.UpdatedCategoryResponse;
import com.example.getirbackend.services.category.CategoryService;
import com.example.getirbackend.utils.pagination.BasePageableModel;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<CreatedCategoryResponse> add(@RequestBody @Valid CreateCategoryRequest request)
			throws Exception {
		return ResponseEntity
				.created(URI.create(
						ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/categories").toUriString()))
				.body(this.categoryService.add(request));
	}
	
	
	@PatchMapping("/")
	public ResponseEntity<UpdatedCategoryResponse> udpate(@RequestBody @Valid UpdateCategoryRequest request) throws Exception{
		return ResponseEntity.ok(this.categoryService.update(request));
	}
	
	@DeleteMapping("/")
	public ResponseEntity<DeletedCategoryResponse> delete(@RequestBody DeleteCategoryRequest request) throws Exception {
		return ResponseEntity.ok(this.categoryService.delete(request));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GetByIdCategoryResponse> getById(@PathVariable long id) throws Exception{
		return ResponseEntity.ok(this.categoryService.getById(id));
	}
	
	
	@GetMapping("/getAll")
	public ResponseEntity<BasePageableModel<GetListCategoryResponse>> getAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
		return ResponseEntity.ok(this.categoryService.getAll(pageNumber, pageSize));
	}
	

}
