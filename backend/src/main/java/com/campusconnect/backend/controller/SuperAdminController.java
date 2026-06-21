package com.campusconnect.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.dto.ClubRequest;
import com.campusconnect.backend.dto.CreateClubRequest;
import com.campusconnect.backend.dto.SuperAdminClubResponse;
import com.campusconnect.backend.service.SuperAdminService;

@RestController
@RequestMapping({"/api/admin/clubs", "/api/superadmin/clubs", "/api/super-admin/clubs"})
@CrossOrigin(origins = "*")
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    public SuperAdminController(SuperAdminService superAdminService) {
        this.superAdminService = superAdminService;
    }

    @GetMapping
    public List<SuperAdminClubResponse> getAllClubs() {
        return superAdminService.getAllClubs();
    }

    @PostMapping
    public SuperAdminClubResponse createClub(@RequestBody CreateClubRequest request) {
        return superAdminService.createClub(request);
    }

    @GetMapping("/{id}")
    public SuperAdminClubResponse getClubById(@PathVariable Long id) {
        return superAdminService.getClubById(id);
    }

    @PutMapping("/{id}")
    public SuperAdminClubResponse updateClub(
            @PathVariable Long id,
            @RequestBody ClubRequest request) {

        return superAdminService.updateClub(id, request);
    }

    @PatchMapping("/{id}")
    public SuperAdminClubResponse patchClub(
            @PathVariable Long id,
            @RequestBody ClubRequest request) {

        return superAdminService.updateClub(id, request);
    }

    @PutMapping("/edit/{id}")
    public SuperAdminClubResponse editClub(
            @PathVariable Long id,
            @RequestBody ClubRequest request) {

        return superAdminService.updateClub(id, request);
    }

    @PatchMapping("/edit/{id}")
    public SuperAdminClubResponse patchEditClub(
            @PathVariable Long id,
            @RequestBody ClubRequest request) {

        return superAdminService.updateClub(id, request);
    }

    @PutMapping("/{id}/edit")
    public SuperAdminClubResponse editClubBySuffix(
            @PathVariable Long id,
            @RequestBody ClubRequest request) {

        return superAdminService.updateClub(id, request);
    }

    @PatchMapping("/{id}/edit")
    public SuperAdminClubResponse patchEditClubBySuffix(
            @PathVariable Long id,
            @RequestBody ClubRequest request) {

        return superAdminService.updateClub(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteClub(@PathVariable Long id) {
        superAdminService.deleteClub(id);
        return ResponseEntity.ok(Map.of("message", "Club deleted successfully"));
    }

    @PutMapping("/{id}/assign-admin/{userId}")
    public SuperAdminClubResponse assignClubAdmin(
            @PathVariable Long id,
            @PathVariable Long userId) {

        return superAdminService.assignClubAdmin(id, userId);
    }
}
