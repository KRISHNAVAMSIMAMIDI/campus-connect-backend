package com.campusconnect.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.campusconnect.backend.dto.SuperAdminUserResponse;
import com.campusconnect.backend.entity.User;

public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    long countByRoleContaining(String role);

    @Query("select new com.campusconnect.backend.dto.SuperAdminUserResponse(u.id, u.name, u.email, u.role) from User u")
    List<SuperAdminUserResponse> findAllSuperAdminUsers();

    @Modifying
    @Query("update User u set u.role = :role where u.id = :id")
    int updateRoleById(@Param("id") Long id, @Param("role") String role);

    @Modifying
    @Query("delete from User u where u.id = :id")
    int deleteUserById(@Param("id") Long id);
}
