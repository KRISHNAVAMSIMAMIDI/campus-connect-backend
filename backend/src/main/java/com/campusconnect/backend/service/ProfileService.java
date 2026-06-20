package com.campusconnect.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusconnect.backend.dto.ProfileResponse;
import com.campusconnect.backend.entity.User;
import com.campusconnect.backend.repository.UserRepository;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    public ProfileResponse getProfileById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new ProfileResponse(user);
    }

    public ProfileResponse getProfileByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new ProfileResponse(user);
    }
}
