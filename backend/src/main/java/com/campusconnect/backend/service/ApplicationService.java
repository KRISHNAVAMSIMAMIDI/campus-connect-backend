
package com.campusconnect.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusconnect.backend.entity.Application;
import com.campusconnect.backend.repository.ApplicationRepository;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository repository;

    public Application apply(Application application) {
        application.setStatus("PENDING");
        return repository.save(application);
    }

    public List<Application> getAllApplications() {
        return repository.findAll();
    }
}