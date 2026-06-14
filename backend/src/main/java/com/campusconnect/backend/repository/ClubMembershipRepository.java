package com.campusconnect.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.backend.entity.ClubMembership;

public interface ClubMembershipRepository extends JpaRepository<ClubMembership, Long> {

    List<ClubMembership> findByUserId(Long userId);

    List<ClubMembership> findByClubId(Long clubId);
}
