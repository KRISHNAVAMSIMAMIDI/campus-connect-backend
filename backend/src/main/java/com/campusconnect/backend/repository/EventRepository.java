package com.campusconnect.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.backend.entity.Event;

public interface EventRepository
        extends JpaRepository<Event, Long> {

    long countByStatusIgnoreCase(String status);

    List<Event> findByClubIdOrderByEventDateDesc(Long clubId);
}
