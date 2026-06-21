package com.campusconnect.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.backend.entity.ClubMembership;

public interface ClubMembershipRepository extends JpaRepository<ClubMembership, Long> {

    List<ClubMembership> findByUserId(Long userId);

    List<ClubMembership> findByClubId(Long clubId);

    Optional<ClubMembership> findByClubIdAndUserId(Long clubId, Long userId);

    Optional<ClubMembership> findFirstByClubIdAndStatus(Long clubId, String status);

    List<ClubMembership> findByClubIdAndStatus(Long clubId, String status);

    boolean existsByUserIdAndStatus(Long userId, String status);

    long countByClubId(Long clubId);

    void deleteByClubId(Long clubId);

    void deleteByUserId(Long userId);
}
