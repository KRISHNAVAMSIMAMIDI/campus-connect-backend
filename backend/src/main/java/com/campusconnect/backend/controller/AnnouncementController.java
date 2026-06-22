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

import com.campusconnect.backend.entity.Announcement;
import com.campusconnect.backend.service.AnnouncementService;

@RestController
@RequestMapping("/api/announcements")
@CrossOrigin("*")
public class AnnouncementController {

    @Autowired
    private AnnouncementService service;

    @PostMapping
    public Announcement createAnnouncement(
            @RequestBody Announcement announcement) {

        return service.createAnnouncement(announcement);
    }

    @GetMapping
    public List<Announcement> getAllAnnouncements() {

        return service.getAllAnnouncements();
    }

    @DeleteMapping("/{id}")
    public void deleteAnnouncement(
            @PathVariable Long id) {

        service.deleteAnnouncement(id);
    }
    @GetMapping("/club/{clubId}")
public List<Announcement> getAnnouncementsByClubId(@PathVariable Long clubId) {
    return service.getAnnouncementsByClubId(clubId);
}
}
