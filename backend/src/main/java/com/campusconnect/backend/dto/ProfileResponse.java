package com.campusconnect.backend.dto;

import com.campusconnect.backend.entity.User;

public class ProfileResponse {

    private Long id;
    private String name;
    private String rollNumber;
    private String branch;
    private Integer passoutYear;
    private String email;
    private String role;

    public ProfileResponse() {
    }

    public ProfileResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.rollNumber = user.getRollNumber();
        this.branch = user.getBranch();
        this.passoutYear = user.getPassoutYear();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Integer getPassoutYear() {
        return passoutYear;
    }

    public void setPassoutYear(Integer passoutYear) {
        this.passoutYear = passoutYear;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
