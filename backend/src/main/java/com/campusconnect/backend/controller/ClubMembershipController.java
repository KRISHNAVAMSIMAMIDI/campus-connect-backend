package com.campusconnect.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.entity.ClubMembership;
import com.campusconnect.backend.service.ClubMembershipService;

@RestController
@RequestMapping("/api/memberships")
@CrossOrigin("*")
public class ClubMembershipController {

    @Autowired
    private ClubMembershipService service;

    @PostMapping
    public ClubMembership joinClub(@RequestBody ClubMembership membership) {
        return service.joinClub(membership);
    }

    @GetMapping
    public List<ClubMembership> getAll() {
        return service.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<ClubMembership> getByUser(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    @GetMapping("/club/{clubId}")
    public List<ClubMembership> getByClub(@PathVariable Long clubId) {
        return service.getByClubId(clubId);
    }

    @DeleteMapping("/{id}")
    public String leaveClub(@PathVariable Long id) {
        service.leaveClub(id);
        return "Left club successfully";
    }
}
