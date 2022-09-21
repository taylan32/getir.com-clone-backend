package com.example.getirbackend.services.city;

import com.example.getirbackend.requests.city.CreateCityRequest;
import com.example.getirbackend.requests.city.DeleteCityRequest;
import com.example.getirbackend.responses.city.CreatedCityResponse;
import com.example.getirbackend.responses.city.DeletedCityResponse;
import com.example.getirbackend.responses.city.GetListCityResponse;
import com.example.getirbackend.utils.pagination.BasePageableModel;

public interface CityService {
	
	CreatedCityResponse add(CreateCityRequest request) throws Exception;
	
	BasePageableModel<GetListCityResponse> getAll(int pageNumber, int pageSize);
	
	DeletedCityResponse delete(DeleteCityRequest request) throws Exception;

}
