package com.example.getirbackend.services.category;

import com.example.getirbackend.requests.category.CreateCategoryRequest;
import com.example.getirbackend.requests.category.DeleteCategoryRequest;
import com.example.getirbackend.requests.category.UpdateCategoryRequest;
import com.example.getirbackend.responses.category.CreatedCategoryResponse;
import com.example.getirbackend.responses.category.DeletedCategoryResponse;
import com.example.getirbackend.responses.category.GetByIdCategoryResponse;
import com.example.getirbackend.responses.category.GetListCategoryResponse;
import com.example.getirbackend.responses.category.UpdatedCategoryResponse;
import com.example.getirbackend.utils.pagination.BasePageableModel;

public interface CategoryService {

	CreatedCategoryResponse add(CreateCategoryRequest request) throws Exception;

	UpdatedCategoryResponse update(UpdateCategoryRequest request) throws Exception;
	
	DeletedCategoryResponse delete(DeleteCategoryRequest request) throws Exception;

	GetByIdCategoryResponse getById(long id) throws Exception;
	
	BasePageableModel<GetListCategoryResponse> getAll(int pageNumber, int pageSize);
	
	
}
