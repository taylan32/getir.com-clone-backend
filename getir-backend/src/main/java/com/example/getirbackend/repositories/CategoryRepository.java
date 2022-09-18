package com.example.getirbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.getirbackend.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query("FROM Category where id = :id")
	Category getById(long id);
	
	boolean existsById(long id);
	
	Category getByName(String name);
	
}
