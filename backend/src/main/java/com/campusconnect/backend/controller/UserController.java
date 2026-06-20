package com.campusconnect.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.dto.ChangePasswordRequest;
import com.campusconnect.backend.entity.User;
import com.campusconnect.backend.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile/{email}")
    public ResponseEntity<?> getProfile(@PathVariable String email) {
        return userService.getProfileByEmail(email)
                .<ResponseEntity<?>>map((User user) -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.status(404).body("User not found"));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        if (request.getEmail() == null || request.getOldPassword() == null || request.getNewPassword() == null) {
            return ResponseEntity.badRequest().body("Email, old password, and new password are required");
        }

        boolean success = userService.changePassword(request.getEmail(), request.getOldPassword(), request.getNewPassword());
        
        if (success) {
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}
