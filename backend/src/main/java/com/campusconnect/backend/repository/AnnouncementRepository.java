package com.campusconnect.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.backend.entity.Announcement;

public interface AnnouncementRepository
        extends JpaRepository<Announcement, Long> {
}
