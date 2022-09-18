package com.example.getirbackend.services.category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.getirbackend.models.Category;
import com.example.getirbackend.repositories.CategoryRepository;
import com.example.getirbackend.requests.category.CreateCategoryRequest;
import com.example.getirbackend.requests.category.DeleteCategoryRequest;
import com.example.getirbackend.requests.category.UpdateCategoryRequest;
import com.example.getirbackend.responses.category.CreatedCategoryResponse;
import com.example.getirbackend.responses.category.DeletedCategoryResponse;
import com.example.getirbackend.responses.category.GetByIdCategoryResponse;
import com.example.getirbackend.responses.category.GetListCategoryResponse;
import com.example.getirbackend.responses.category.UpdatedCategoryResponse;
import com.example.getirbackend.services.rules.CategoryBusinessRules;
import com.example.getirbackend.utils.pagination.BasePageableModel;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	private ModelMapper mapper;
	private CategoryBusinessRules categoryBusinessRules;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper,
			CategoryBusinessRules categoryBusinessRules) {
		this.categoryRepository = categoryRepository;
		this.mapper = mapper;
		this.categoryBusinessRules = categoryBusinessRules;
	}

	@Override
	public CreatedCategoryResponse add(CreateCategoryRequest request) throws Exception {

		this.categoryBusinessRules.categoryNameCanNotDuplicate(request.getName());

		Category mappedCategory = this.mapper.map(request, Category.class);
		Category addedCategory = this.categoryRepository.save(mappedCategory);
		CreatedCategoryResponse createdCategoryResponse = this.mapper.map(addedCategory, CreatedCategoryResponse.class);

		return createdCategoryResponse;

	}

	@Override
	public UpdatedCategoryResponse update(UpdateCategoryRequest request) throws Exception {

		this.categoryBusinessRules.categoryShouldExist(request.getId());
		this.categoryBusinessRules.categoryNameCanNotDuplicate(request.getName());

		Category mappedCategory = this.mapper.map(request, Category.class);
		Category updatedCategory = this.categoryRepository.save(mappedCategory);
		UpdatedCategoryResponse updatedCategoryResponse = this.mapper.map(updatedCategory,
				UpdatedCategoryResponse.class);

		return updatedCategoryResponse;

	}

	@Override
	public DeletedCategoryResponse delete(DeleteCategoryRequest request) throws Exception {

		this.categoryBusinessRules.categoryShouldExist(request.getId());

		Category category = this.categoryRepository.getById(request.getId());
		DeletedCategoryResponse mappedCategory = this.mapper.map(category, DeletedCategoryResponse.class);
		this.categoryRepository.delete(category);

		return mappedCategory;
	}

	@Override
	public GetByIdCategoryResponse getById(long id) throws Exception {

		this.categoryBusinessRules.categoryShouldExist(id);

		Category category = this.categoryRepository.getById(id);
		GetByIdCategoryResponse mappedCategory = this.mapper.map(category, GetByIdCategoryResponse.class);

		return mappedCategory;
	}
	

	@Override
	public BasePageableModel<GetListCategoryResponse> getAll(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
		int size = this.categoryRepository.findAll().size();
		List<Category> categories = this.categoryRepository.findAll(pageable).stream().collect(Collectors.toList());
		List<GetListCategoryResponse> categoryResponses = new ArrayList<>();
		categories.forEach(c -> {
			GetListCategoryResponse response = this.mapper.map(c, GetListCategoryResponse.class);
			categoryResponses.add(response);
		});
		BasePageableModel<GetListCategoryResponse> pageableCategoryResponse = new BasePageableModel<>(categoryResponses,
				pageNumber, pageSize, size);
		return pageableCategoryResponse;
	}

}
