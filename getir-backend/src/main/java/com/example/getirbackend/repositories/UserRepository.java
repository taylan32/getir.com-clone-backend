package com.example.getirbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.getirbackend.models.User;


public interface UserRepository extends JpaRepository<User, Long> {

	@Query("FROM User where id=:id")
	User getById(long id);
	
	User getByEmail(String email);
	
	boolean existsByEmail(String email);
	
	boolean existsById(long id);
	
}
