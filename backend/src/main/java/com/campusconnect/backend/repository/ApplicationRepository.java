package com.campusconnect.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.backend.entity.Application;

public interface ApplicationRepository
        extends JpaRepository<Application, Long> {
}
