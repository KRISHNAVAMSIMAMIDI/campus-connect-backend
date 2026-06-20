package com.campusconnect.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.dto.ProfileResponse;
import com.campusconnect.backend.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{userId}")
    public ProfileResponse getProfile(@PathVariable Long userId) {
        return profileService.getProfileById(userId);
    }

    @GetMapping("/email/{email}")
    public ProfileResponse getProfileByEmail(@PathVariable String email) {
        return profileService.getProfileByEmail(email);
    }

    @GetMapping
    public ProfileResponse getProfileByEmailParam(@RequestParam String email) {
        return profileService.getProfileByEmail(email);
    }
}
