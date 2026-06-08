package com.campusconnect.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.backend.entity.Event;

public interface EventRepository
        extends JpaRepository<Event, Long> {

}