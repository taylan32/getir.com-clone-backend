package com.example.getirbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.getirbackend.models.City;

public interface CityRepository extends JpaRepository<City, Long> {

	@Query("FROM City where id=:id")
	City getById(long id);
	
	City getByName(String name);
	
	boolean existsById(long id);
	
	boolean existsByName(String name);
	
}
