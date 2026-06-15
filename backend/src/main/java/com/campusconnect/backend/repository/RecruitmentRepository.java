package com.campusconnect.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.backend.entity.Recruitment;

public interface RecruitmentRepository
        extends JpaRepository<Recruitment, Long> {
}
