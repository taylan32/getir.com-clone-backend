package com.example.getirbackend.services.city;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.getirbackend.models.City;
import com.example.getirbackend.repositories.CityRepository;
import com.example.getirbackend.requests.city.CreateCityRequest;
import com.example.getirbackend.requests.city.DeleteCityRequest;
import com.example.getirbackend.responses.city.CreatedCityResponse;
import com.example.getirbackend.responses.city.DeletedCityResponse;
import com.example.getirbackend.responses.city.GetListCityResponse;
import com.example.getirbackend.services.rules.CityBusinessRules;
import com.example.getirbackend.utils.pagination.BasePageableModel;

@Service
public class CityServiceImpl implements CityService{

	private CityRepository cityRepository;
	private ModelMapper mapper;
	private CityBusinessRules cityBusinessRules;
	
	@Autowired	
	public CityServiceImpl(CityRepository cityRepository, ModelMapper mapper, CityBusinessRules cityBusinessRules) {
		this.cityRepository = cityRepository;
		this.mapper = mapper;
		this.cityBusinessRules = cityBusinessRules;
	}



	@Override
	public CreatedCityResponse add(CreateCityRequest request) throws Exception {
		
		this.cityBusinessRules.cityNameCanNotDuplicate(request.getName());
		
		City mappedCity = this.mapper.map(request, City.class);
		City createdCity = this.cityRepository.save(mappedCity);
		CreatedCityResponse cityResponse = this.mapper.map(createdCity, CreatedCityResponse.class);
		
		return cityResponse;
		
	}



	@Override
	public BasePageableModel<GetListCityResponse> getAll(int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber -1 , pageSize);
		int size = this.cityRepository.findAll().size();
		List<City> cities = this.cityRepository.findAll(pageable).stream().collect(Collectors.toList());
		List<GetListCityResponse> cityResponses = new ArrayList<>();
		cities.forEach(city -> {
			GetListCityResponse response = this.mapper.map(city, GetListCityResponse.class);
			cityResponses.add(response);
		});
		
		return new BasePageableModel<GetListCityResponse>(cityResponses, pageNumber, pageSize, size);
	}



	@Override
	public DeletedCityResponse delete(DeleteCityRequest request) throws Exception {

		this.cityBusinessRules.cityShouldExist(request.getId());
		
		City city = this.cityRepository.getById(request.getId());
		DeletedCityResponse cityResponse = this.mapper.map(city, DeletedCityResponse.class);
		this.cityRepository.delete(city);
		
		return cityResponse;
		
	}

}
