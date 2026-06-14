package com.campusconnect.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusconnect.backend.entity.ClubMembership;
import com.campusconnect.backend.repository.ClubMembershipRepository;

@Service
public class ClubMembershipService {

    @Autowired
    private ClubMembershipRepository repository;

    public ClubMembership joinClub(ClubMembership membership) {
        return repository.save(membership);
    }

    public List<ClubMembership> getAll() {
        return repository.findAll();
    }

    public List<ClubMembership> getByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<ClubMembership> getByClubId(Long clubId) {
        return repository.findByClubId(clubId);
    }

    public void leaveClub(Long id) {
        repository.deleteById(id);
    }
}
