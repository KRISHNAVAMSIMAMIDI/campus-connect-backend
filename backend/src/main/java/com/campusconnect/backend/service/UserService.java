package com.campusconnect.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusconnect.backend.entity.User;
import com.campusconnect.backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
}
