package com.campusconnect.backend.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campusconnect.backend.dto.ClubAdminResponse;
import com.campusconnect.backend.dto.ClubRequest;
import com.campusconnect.backend.dto.CreateClubRequest;
import com.campusconnect.backend.dto.SuperAdminClubResponse;
import com.campusconnect.backend.entity.Club;
import com.campusconnect.backend.entity.ClubMembership;
import com.campusconnect.backend.entity.User;
import com.campusconnect.backend.exception.ResourceNotFoundException;
import com.campusconnect.backend.repository.ClubMembershipRepository;
import com.campusconnect.backend.repository.ClubRepository;
import com.campusconnect.backend.repository.UserRepository;

@Service
public class SuperAdminService {

    private static final String CLUB_ADMIN_ROLE = "CLUB_ADMIN";

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final ClubMembershipRepository clubMembershipRepository;

    public SuperAdminService(
            ClubRepository clubRepository,
            UserRepository userRepository,
            ClubMembershipRepository clubMembershipRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
        this.clubMembershipRepository = clubMembershipRepository;
    }

    public List<SuperAdminClubResponse> getAllClubs() {
        return clubRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public SuperAdminClubResponse getClubById(Long id) {
        return toResponse(findClub(id));
    }

    @Transactional
    public SuperAdminClubResponse createClub(CreateClubRequest request) {
        User admin = userRepository.findByEmail(request.getClubAdminEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Club admin user not found"));

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

        Club savedClub = clubRepository.save(club);
        setClubAdmin(savedClub.getId(), admin);

        return toResponse(savedClub);
    }

    @Transactional
    public SuperAdminClubResponse updateClub(Long id, ClubRequest request) {
        Club club = findClub(id);

        if (request.getName() != null) {
            club.setName(request.getName());
        }
        if (request.getTagline() != null) {
            club.setTagline(request.getTagline());
        }
        if (request.getDescription() != null) {
            club.setDescription(request.getDescription());
        }
        if (request.getAbout() != null) {
            club.setAbout(request.getAbout());
        }
        if (request.getVision() != null) {
            club.setVision(request.getVision());
        }
        if (request.getFaculty() != null) {
            club.setFaculty(request.getFaculty());
        }
        if (request.getMembers() != null) {
            club.setMembers(request.getMembers());
        }
        if (request.getLogoUrl() != null) {
            club.setLogoUrl(request.getLogoUrl());
        }
        if (request.getBannerUrl() != null) {
            club.setBannerUrl(request.getBannerUrl());
        }
        if (request.getRecruitment() != null) {
            club.setRecruitment(request.getRecruitment());
        }
        if (request.getInstagramUrl() != null) {
            club.setInstagramUrl(request.getInstagramUrl());
        }
        if (request.getLinkedinUrl() != null) {
            club.setLinkedinUrl(request.getLinkedinUrl());
        }

        return toResponse(clubRepository.save(club));
    }

    @Transactional
    public void deleteClub(Long id) {
        findClub(id);

        List<Long> affectedAdminUserIds = clubMembershipRepository.findByClubIdAndStatus(id, CLUB_ADMIN_ROLE)
                .stream()
                .map(ClubMembership::getUserId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        clubMembershipRepository.deleteByClubId(id);
        clubRepository.deleteById(id);

        affectedAdminUserIds.forEach(this::removeClubAdminRoleIfUnused);
    }

    @Transactional
    public SuperAdminClubResponse assignClubAdmin(Long clubId, Long userId) {
        Club club = findClub(clubId);
        User newAdmin = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Long> previousAdminUserIds = clubMembershipRepository.findByClubIdAndStatus(clubId, CLUB_ADMIN_ROLE)
                .stream()
                .map(ClubMembership::getUserId)
                .filter(Objects::nonNull)
                .filter(previousUserId -> !previousUserId.equals(userId))
                .distinct()
                .collect(Collectors.toList());

        previousAdminUserIds.forEach(previousUserId ->
                clubMembershipRepository.findByClubIdAndUserId(clubId, previousUserId)
                        .ifPresent(membership -> {
                            membership.setStatus("MEMBER");
                            clubMembershipRepository.save(membership);
                        }));

        setClubAdmin(clubId, newAdmin);
        previousAdminUserIds.forEach(this::removeClubAdminRoleIfUnused);

        return toResponse(club);
    }

    private void setClubAdmin(Long clubId, User user) {
        ClubMembership membership = clubMembershipRepository.findByClubIdAndUserId(clubId, user.getId())
                .orElseGet(ClubMembership::new);

        membership.setClubId(clubId);
        membership.setUserId(user.getId());
        membership.setStatus(CLUB_ADMIN_ROLE);
        clubMembershipRepository.save(membership);

        addRole(user, CLUB_ADMIN_ROLE);
    }

    private SuperAdminClubResponse toResponse(Club club) {
        SuperAdminClubResponse response = new SuperAdminClubResponse();
        response.setId(club.getId());
        response.setName(club.getName());
        response.setTagline(club.getTagline());
        response.setDescription(club.getDescription());
        response.setAbout(club.getAbout());
        response.setVision(club.getVision());
        response.setFaculty(club.getFaculty());
        response.setMembers(club.getMembers());
        response.setTotalMembers(clubMembershipRepository.countByClubId(club.getId()));
        response.setLogoUrl(club.getLogoUrl());
        response.setBannerUrl(club.getBannerUrl());
        response.setRecruitment(club.getRecruitment());
        response.setInstagramUrl(club.getInstagramUrl());
        response.setLinkedinUrl(club.getLinkedinUrl());
        response.setClubAdmin(findClubAdmin(club.getId()));
        return response;
    }

    private ClubAdminResponse findClubAdmin(Long clubId) {
        return clubMembershipRepository.findFirstByClubIdAndStatus(clubId, CLUB_ADMIN_ROLE)
                .map(ClubMembership::getUserId)
                .filter(Objects::nonNull)
                .flatMap(userRepository::findById)
                .map(user -> new ClubAdminResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()))
                .orElse(null);
    }

    private Club findClub(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Club not found"));
    }

    private void addRole(User user, String role) {
        if (hasRole(user, role)) {
            return;
        }

        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole(role);
        } else {
            user.setRole(user.getRole() + "," + role);
        }

        userRepository.save(user);
    }

    private void removeClubAdminRoleIfUnused(Long userId) {
        if (clubMembershipRepository.existsByUserIdAndStatus(userId, CLUB_ADMIN_ROLE)) {
            return;
        }

        userRepository.findById(userId).ifPresent(user -> {
            String updatedRoles = Arrays.stream(safeRoles(user).split(","))
                    .map(String::trim)
                    .filter(role -> !role.isBlank())
                    .filter(role -> !role.equals(CLUB_ADMIN_ROLE))
                    .collect(Collectors.joining(","));

            user.setRole(updatedRoles);
            userRepository.save(user);
        });
    }

    private boolean hasRole(User user, String role) {
        return Arrays.stream(safeRoles(user).split(","))
                .map(String::trim)
                .anyMatch(existingRole -> existingRole.equals(role));
    }

    private String safeRoles(User user) {
        return user.getRole() == null ? "" : user.getRole();
    }
}
