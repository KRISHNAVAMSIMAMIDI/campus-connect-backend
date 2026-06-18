package com.campusconnect.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusconnect.backend.dto.RegisterRequest;
import com.campusconnect.backend.entity.EmailOtp;
import com.campusconnect.backend.entity.User;
import com.campusconnect.backend.repository.EmailOtpRepository;
import com.campusconnect.backend.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailOtpRepository emailOtpRepository;

    public User register(RegisterRequest request) {

        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // Check OTP verification
        EmailOtp emailOtp = emailOtpRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Please verify OTP first"));

        if (!Boolean.TRUE.equals(emailOtp.getVerified())) {
            throw new RuntimeException("Please verify OTP first");
        }

        User user = new User();

        user.setName(request.getName());
        user.setRollNumber(request.getRollNumber());
        user.setBranch(request.getBranch());
        user.setPassoutYear(request.getPassoutYear());

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        user.setRole("STUDENT");

        User savedUser = userRepository.save(user);

        // Remove OTP after successful registration
        emailOtpRepository.deleteByEmail(request.getEmail());

        return savedUser;
    }

    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}