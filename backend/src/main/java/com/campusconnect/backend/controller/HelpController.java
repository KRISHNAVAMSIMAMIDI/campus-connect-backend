package com.campusconnect.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.dto.HelpResponse;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HelpController {

    @GetMapping("/help")
    public HelpResponse help() {
        HelpResponse res = new HelpResponse();
        res.setProjectName("CampusConnect");
        res.setDescription("Centralized platform for clubs, events and recruitments.");
        res.setSupportEmail("support@campusconnect.com");
        res.setCollege("Gayatri Vidya Parishad College of Engineering");
        res.setFaq(Arrays.asList(
                "How do I join a club?",
                "How do I register for events?",
                "How do I apply for recruitments?",
                "How do I update my profile?"
        ));

        return res;
    }
}
