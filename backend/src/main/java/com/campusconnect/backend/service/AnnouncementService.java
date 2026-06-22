package com.campusconnect.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusconnect.backend.entity.Announcement;
import com.campusconnect.backend.repository.AnnouncementRepository;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository repository;

    public Announcement createAnnouncement(
            Announcement announcement) {

        return repository.save(announcement);
    }

    public List<Announcement> getAllAnnouncements() {

        return repository.findAll();
    }

    public void deleteAnnouncement(Long id) {

        repository.deleteById(id);
    }

    public List<Announcement> getAnnouncementsByClubId(Long clubId) {
        return repository.findByClubIdOrderByDateDesc(clubId);
    }
}
