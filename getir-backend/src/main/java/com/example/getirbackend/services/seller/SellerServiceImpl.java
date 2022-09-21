package com.example.getirbackend.services.seller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.getirbackend.models.Seller;
import com.example.getirbackend.repositories.SellerRepository;
import com.example.getirbackend.requests.seller.CreateSellerRequest;
import com.example.getirbackend.requests.seller.UpdateSellerRequest;
import com.example.getirbackend.responses.seller.CreatedSellerResponse;
import com.example.getirbackend.responses.seller.GetByIdSellerResponse;
import com.example.getirbackend.responses.seller.GetListSellerResponse;
import com.example.getirbackend.responses.seller.UpdatedSellerResponse;
import com.example.getirbackend.services.rules.SellerBusinessRules;
import com.example.getirbackend.utils.pagination.BasePageableModel;

@Service
public class SellerServiceImpl implements SellerService {

	private SellerRepository sellerRepository;
	private ModelMapper mapper;
	private SellerBusinessRules sellerBusinessRules;
	
	@Autowired
	public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper mapper,
			SellerBusinessRules sellerBusinessRules) {
		this.sellerRepository = sellerRepository;
		this.mapper = mapper;
		this.sellerBusinessRules = sellerBusinessRules;
	}



	@Override
	public CreatedSellerResponse add(CreateSellerRequest request) throws Exception {
		this.sellerBusinessRules.sellerNameCanNotDuplicate(request.getName());
		
		Seller mappedSeller = this.mapper.map(request, Seller.class);
		Seller addedSeller = this.sellerRepository.save(mappedSeller);
		CreatedSellerResponse createdSellerResponse = this.mapper.map(addedSeller, CreatedSellerResponse.class);
		
		return createdSellerResponse;
		
	}



	@Override
	public UpdatedSellerResponse update(UpdateSellerRequest request) throws Exception {
		
		this.sellerBusinessRules.sellerShouldExist(request.getId());
		
		
		Seller seller = this.sellerRepository.getById(request.getId());
		Seller mappedSeller = this.mapper.map(request, Seller.class);
		Seller updatedSeller = this.sellerRepository.save(mappedSeller);
		UpdatedSellerResponse response = this.mapper.map(updatedSeller, UpdatedSellerResponse.class);
		
		return response;
	}



	@Override
	public GetByIdSellerResponse getById(long id) throws Exception {

		this.sellerBusinessRules.sellerShouldExist(id);
		
		Seller seller = this.sellerRepository.getById(id);
		GetByIdSellerResponse response = this.mapper.map(seller, GetByIdSellerResponse.class);
		
		return response;

	}



	@Override
	public BasePageableModel<GetListSellerResponse> getAll(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber -1 , pageSize);
		int size = this.sellerRepository.findAll().size();
		List<Seller> sellers = this.sellerRepository.findAll(pageable).stream().collect(Collectors.toList());
		List<GetListSellerResponse> sellerResponses = new ArrayList<>();
		sellers.forEach(seller -> {
			GetListSellerResponse response = this.mapper.map(seller, GetListSellerResponse.class);
			sellerResponses.add(response);
		});
		return new BasePageableModel<GetListSellerResponse>(sellerResponses, pageNumber, pageSize, size);
	}

}
