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

import com.campusconnect.backend.entity.Recruitment;
import com.campusconnect.backend.service.RecruitmentService;

@RestController
@RequestMapping("/api/recruitments")
@CrossOrigin("*")
public class RecruitmentController {

    @Autowired
    private RecruitmentService service;

    @PostMapping
    public Recruitment addRecruitment(
            @RequestBody Recruitment recruitment) {

        return service.addRecruitment(recruitment);
    }

    @GetMapping
    public List<Recruitment> getAllRecruitments() {
        return service.getAllRecruitments();
    }

    @DeleteMapping("/{id}")
    public String deleteRecruitment(
            @PathVariable Long id) {

        service.deleteRecruitment(id);

        return "Recruitment Deleted Successfully";
    }
    @GetMapping("/club/{clubId}")
public List<Recruitment> getRecruitmentsByClubId(
        @PathVariable Long clubId) {

    return service.getRecruitmentsByClubId(clubId);
}
}