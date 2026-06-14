package com.campusconnect.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.entity.Club;
import com.campusconnect.backend.service.ClubService;

@RestController
@RequestMapping("/api/clubs")
@CrossOrigin(origins = "*")
public class ClubController {

    @Autowired
    private ClubService service;

    // Add Club
    @PostMapping
    public Club addClub(@RequestBody Club club) {
        return service.addClub(club);
    }

    // Get All Clubs
    @GetMapping
    public List<Club> getAllClubs() {
        return service.getAllClubs();
    }

    // Get Club By Id
    @GetMapping("/{id}")
    public Club getClub(@PathVariable Long id) {
        return service.getClub(id);
    }

    // Update Club
    @PutMapping("/{id}")
    public Club updateClub(
            @PathVariable Long id,
            @RequestBody Club club) {

        return service.updateClub(id, club);
    }

    // Delete Club
    @DeleteMapping("/{id}")
    public String deleteClub(@PathVariable Long id) {

        service.deleteClub(id);

        return "Club Deleted Successfully";
    }
}