package com.campusconnect.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.dto.SuperAdminUserResponse;
import com.campusconnect.backend.dto.UpdateUserRoleRequest;
import com.campusconnect.backend.service.UserService;

@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin(origins = "*")
public class SuperAdminUserController {

    private final UserService userService;

    public SuperAdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<SuperAdminUserResponse> getAllUsers() {
        return userService.getAllUsersForSuperAdmin();
    }

    @PutMapping("/{id}/role")
    public SuperAdminUserResponse updateUserRole(
            @PathVariable Long id,
            @RequestBody UpdateUserRoleRequest request) {
        return userService.updateUserRole(id, request.getRole());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        userService.deleteUserForSuperAdmin(id);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }
}
