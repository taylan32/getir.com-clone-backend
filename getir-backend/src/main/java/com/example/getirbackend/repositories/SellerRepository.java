package com.example.getirbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.getirbackend.models.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>{

	@Query("FROM Seller where id=:id")
	Seller getById(long id);
	
	boolean existsByName(String name);
	
	boolean existsById(long id);
	
	Seller getByName(String name);
	
}
