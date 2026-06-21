package com.campusconnect.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.dto.SuperAdminAnalyticsResponse;
import com.campusconnect.backend.service.SuperAdminAnalyticsService;

@RestController
@RequestMapping("/api/admin/analytics")
@CrossOrigin(origins = "*")
public class SuperAdminAnalyticsController {

    private final SuperAdminAnalyticsService analyticsService;

    public SuperAdminAnalyticsController(SuperAdminAnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping
    public SuperAdminAnalyticsResponse getAnalytics() {
        return analyticsService.getAnalytics();
    }
}
