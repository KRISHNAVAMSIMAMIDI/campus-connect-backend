package com.campusconnect.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.backend.entity.Club;

public interface ClubRepository
        extends JpaRepository<Club, Long> {

    Optional<Club> findByAdminEmail(String adminEmail);

}
