package com.campusconnect.backend.controller;

import com.campusconnect.backend.dto.CreateClubRequest;
import com.campusconnect.backend.entity.Club;
import com.campusconnect.backend.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/create-club")
    public Club createClub(
            @RequestBody CreateClubRequest request) {

        return adminService.createClub(request);
    }
}