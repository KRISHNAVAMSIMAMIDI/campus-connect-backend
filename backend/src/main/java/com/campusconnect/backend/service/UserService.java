package com.campusconnect.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.campusconnect.backend.entity.User;
import com.campusconnect.backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        boolean passwordMatches;
        if (storedPassword.startsWith("$2a$") || storedPassword.startsWith("$2b$") || storedPassword.startsWith("$2y$")) {
            try {
                passwordMatches = passwordEncoder.matches(oldPassword, storedPassword);
            } catch (IllegalArgumentException ex) {
                passwordMatches = false;
            }
        } else {
            passwordMatches = storedPassword.equals(oldPassword);
        }

        if (!passwordMatches) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return true;
    }
}
