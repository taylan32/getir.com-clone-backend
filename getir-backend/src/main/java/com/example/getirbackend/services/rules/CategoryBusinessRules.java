package com.example.getirbackend.services.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.getirbackend.exceptions.BusinessException;
import com.example.getirbackend.exceptions.NotFoundException;
import com.example.getirbackend.models.Category;
import com.example.getirbackend.repositories.CategoryRepository;

@Service
public class CategoryBusinessRules {

	private CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryBusinessRules(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public void categoryNameCanNotDuplicate(String name) throws Exception {
		Category category = this.categoryRepository.getByName(name);
		if(category != null)  {
			throw new BusinessException("Bu katagori daha önce eklenmiş");
		}
	}
	
	public void categoryShouldExist(long id) throws Exception{
		Category category = this.categoryRepository.getById(id);
		if(category == null) {
			throw new NotFoundException("Kategori bulunamadı");
		}
	}
	
}
