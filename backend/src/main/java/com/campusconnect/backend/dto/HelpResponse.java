package com.campusconnect.backend.dto;

import java.util.List;

public class HelpResponse {

    private String projectName;
    private String description;
    private String supportEmail;
    private String college;
    private List<String> faq;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupportEmail() {
        return supportEmail;
    }

    public void setSupportEmail(String supportEmail) {
        this.supportEmail = supportEmail;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public List<String> getFaq() {
        return faq;
    }

    public void setFaq(List<String> faq) {
        this.faq = faq;
    }
}
