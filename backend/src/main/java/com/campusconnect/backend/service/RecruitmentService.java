package com.campusconnect.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusconnect.backend.entity.Recruitment;
import com.campusconnect.backend.repository.RecruitmentRepository;

@Service
public class RecruitmentService {

    @Autowired
    private RecruitmentRepository repository;

    public Recruitment addRecruitment(Recruitment recruitment) {
        return repository.save(recruitment);
    }

    public List<Recruitment> getAllRecruitments() {
        return repository.findAll();
    }

    public void deleteRecruitment(Long id) {
        repository.deleteById(id);
    }
    public List<Recruitment> getRecruitmentsByClubId(
            Long clubId) {

        return repository.findByClubId(clubId);
    }
}