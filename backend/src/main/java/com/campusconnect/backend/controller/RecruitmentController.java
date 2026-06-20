package com.campusconnect.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}