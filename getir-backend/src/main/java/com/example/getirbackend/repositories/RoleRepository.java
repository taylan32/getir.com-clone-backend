package com.example.getirbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.getirbackend.models.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

	boolean existsByName(String name);
	boolean existsById(long id);
	
	@Query("FROM Role where id=:id")
	Role getById(long id);
	
}
