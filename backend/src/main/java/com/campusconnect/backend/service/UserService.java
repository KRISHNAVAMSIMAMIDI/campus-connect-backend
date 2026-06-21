package com.campusconnect.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campusconnect.backend.dto.SuperAdminUserResponse;
import com.campusconnect.backend.entity.User;
import com.campusconnect.backend.exception.ResourceNotFoundException;
import com.campusconnect.backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<User> getProfileByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean changePassword(String email, String oldPassword, String newPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return false;
        }

        User user = userOpt.get();
        String storedPassword = user.getPassword();

        if (storedPassword == null) {
            return false;
        }

        if (!storedPassword.equals(oldPassword)) {
            return false;
        }

        user.setPassword(newPassword);
        userRepository.save(user);

        return true;
    }

    public List<SuperAdminUserResponse> getAllUsersForSuperAdmin() {
        return jdbcTemplate.query(
                "select id, name, email, role from users order by id",
                (rs, rowNum) -> new SuperAdminUserResponse(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role")));
    }

    @Transactional
    public SuperAdminUserResponse updateUserRole(Long id, String role) {
        String normalizedRole = normalizeSupportedRole(role);

        if ("STUDENT".equals(normalizedRole)) {
            demoteClubAdminMemberships(id);
        }

        int updatedRows = jdbcTemplate.update(
                "update users set role = ? where id = ?",
                normalizedRole,
                id);

        if (updatedRows == 0) {
            throw new ResourceNotFoundException("User not found");
        }

        return getUserResponse(id);
    }

    @Transactional
    public void deleteUserForSuperAdmin(Long id) {
        Integer userExists = jdbcTemplate.queryForObject(
                "select count(*) from users where id = ?",
                Integer.class,
                id);

        if (userExists == null || userExists == 0) {
            throw new ResourceNotFoundException("User not found");
        }

        jdbcTemplate.update("delete from club_memberships where user_id = ?", id);
        jdbcTemplate.update("delete from users where id = ?", id);
    }

    private String normalizeSupportedRole(String role) {
        if (role == null || role.isBlank()) {
            throw new RuntimeException("Role is required");
        }

        String normalizedRole = role.trim().toUpperCase();

        if (!"STUDENT".equals(normalizedRole) && !"CLUB_ADMIN".equals(normalizedRole)) {
            throw new RuntimeException("Supported roles are STUDENT and CLUB_ADMIN");
        }

        return normalizedRole;
    }

    private void demoteClubAdminMemberships(Long userId) {
        jdbcTemplate.update(
                "update club_memberships set status = 'MEMBER' where user_id = ? and status = 'CLUB_ADMIN'",
                userId);
    }

    private SuperAdminUserResponse getUserResponse(Long id) {
        List<SuperAdminUserResponse> users = jdbcTemplate.query(
                "select id, name, email, role from users where id = ?",
                (rs, rowNum) -> new SuperAdminUserResponse(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role")),
                id);

        if (users.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        return users.get(0);
    }
}
