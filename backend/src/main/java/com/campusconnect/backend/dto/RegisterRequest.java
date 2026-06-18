package com.campusconnect.backend.dto;

public class RegisterRequest {

    private String name;
    private String rollNumber;
    private String branch;
    private Integer passoutYear;
    private String email;
    private String password;

    public RegisterRequest() {
    }

    public RegisterRequest(
            String name,
            String rollNumber,
            String branch,
            Integer passoutYear,
            String email,
            String password) {

        this.name = name;
        this.rollNumber = rollNumber;
        this.branch = branch;
        this.passoutYear = passoutYear;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}