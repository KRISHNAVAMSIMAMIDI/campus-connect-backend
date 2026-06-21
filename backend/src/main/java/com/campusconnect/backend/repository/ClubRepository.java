package com.campusconnect.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.campusconnect.backend.entity.Club;

public interface ClubRepository
        extends JpaRepository<Club, Long> {

}
