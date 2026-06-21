package com.campusconnect.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusconnect.backend.dto.CreateClubRequest;
import com.campusconnect.backend.entity.Club;
import com.campusconnect.backend.entity.User;
import com.campusconnect.backend.repository.ClubRepository;
import com.campusconnect.backend.repository.UserRepository;

@Service
public class AdminService {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private UserRepository userRepository;

    public Club createClub(
            CreateClubRequest request) {

        User user = userRepository
                .findByEmail(request.getClubAdminEmail())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"));

        Club club = new Club();

        club.setName(request.getName());
        club.setFaculty(request.getFaculty());

        club.setTagline("Update Club Tagline");
        club.setDescription("Description not updated yet");
        club.setAbout("About section not updated yet");
        club.setVision("Vision not updated yet");

        club.setMembers(0);

        club.setLogoUrl("");
        club.setBannerUrl("");

        club.setRecruitment("Closed");

        club.setInstagramUrl("");
        club.setLinkedinUrl("");

        String role = user.getRole();

        if (!role.contains("CLUB_ADMIN")) {

            user.setRole(
                    role + ",CLUB_ADMIN");

            userRepository.save(user);
        }

        return clubRepository.save(club);
    }
}