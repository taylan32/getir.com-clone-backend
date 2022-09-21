package com.example.getirbackend.services.seller;

import com.example.getirbackend.requests.seller.CreateSellerRequest;
import com.example.getirbackend.requests.seller.UpdateSellerRequest;
import com.example.getirbackend.responses.seller.CreatedSellerResponse;
import com.example.getirbackend.responses.seller.GetByIdSellerResponse;
import com.example.getirbackend.responses.seller.GetListSellerResponse;
import com.example.getirbackend.responses.seller.UpdatedSellerResponse;
import com.example.getirbackend.utils.pagination.BasePageableModel;

public interface SellerService {

	CreatedSellerResponse add(CreateSellerRequest request) throws Exception;

	UpdatedSellerResponse update(UpdateSellerRequest request) throws Exception;
	
	GetByIdSellerResponse getById(long id) throws Exception;
	
	BasePageableModel<GetListSellerResponse> getAll(int pageNumber, int pageSize);
	
}
