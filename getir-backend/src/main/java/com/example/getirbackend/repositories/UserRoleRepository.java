package com.example.getirbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.getirbackend.models.UserRole;
import com.example.getirbackend.responses.userRole.GetListUserRoleResponse;
import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	@Query("FROM UserRole where id=:id")
	UserRole getById(long id);

	UserRole getByUserIdAndRoleId(long userId, long roleId);
	
	List<UserRole> getByUserId(long userId);

	boolean existsByUserIdAndRoleId(long userId, long roleId);

	@Query(value = "select u.id, u.email, r.name from users u "
			+ "inner join user_roles as ur on u.id = ur.user_id "
			+ "inner join roles as r on ur.role_id = r.id "
			+ "where u.id = userId", nativeQuery = true)
	List<GetListUserRoleResponse> getUserRoleWithDetail(long userId);
}
