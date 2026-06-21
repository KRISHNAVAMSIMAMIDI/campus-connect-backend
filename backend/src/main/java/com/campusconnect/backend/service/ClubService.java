package com.campusconnect.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusconnect.backend.entity.Club;
import com.campusconnect.backend.repository.ClubRepository;

@Service
public class ClubService {

    @Autowired
    private ClubRepository repository;

    public Club addClub(Club club) {
        return repository.save(club);
    }

    public List<Club> getAllClubs() {
        return repository.findAll();
    }

    public Club getClub(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Club updateClub(Long id, Club updatedClub) {

        Club club = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found"));

        club.setName(updatedClub.getName());
        club.setTagline(updatedClub.getTagline());
        club.setDescription(updatedClub.getDescription());
        club.setAbout(updatedClub.getAbout());
        club.setVision(updatedClub.getVision());
        club.setFaculty(updatedClub.getFaculty());
        club.setMembers(updatedClub.getMembers());
        club.setLogoUrl(updatedClub.getLogoUrl());
        club.setBannerUrl(updatedClub.getBannerUrl());
        club.setRecruitment(updatedClub.getRecruitment());
        club.setInstagramUrl(updatedClub.getInstagramUrl());
        club.setLinkedinUrl(updatedClub.getLinkedinUrl());

        return repository.save(club);
    }

    public void deleteClub(Long id) {
        repository.deleteById(id);
    }
    public Club getClubByAdminEmail(String email) {

    return repository
            .findByAdminEmail(email)
            .orElse(null);
}
}