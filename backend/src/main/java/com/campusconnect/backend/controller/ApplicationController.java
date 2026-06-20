package com.campusconnect.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.entity.Application;
import com.campusconnect.backend.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin("*")
public class ApplicationController {

    @Autowired
    private ApplicationService service;

    @PostMapping
    public Application apply(
            @RequestBody Application application) {

        return service.apply(application);
    }

    @GetMapping
    public List<Application> getAllApplications() {
        return service.getAllApplications();
    }
    @PutMapping("/{id}/approve")
public Application approveApplication(
        @PathVariable Long id) {

    return service.approveApplication(id);
}

@PutMapping("/{id}/reject")
public Application rejectApplication(
        @PathVariable Long id) {

    return service.rejectApplication(id);
}
}
