package com.campusconnect.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.backend.entity.Recruitment;

public interface RecruitmentRepository
        extends JpaRepository<Recruitment, Long> {

    List<Recruitment> findByClubId(Long clubId);

}
