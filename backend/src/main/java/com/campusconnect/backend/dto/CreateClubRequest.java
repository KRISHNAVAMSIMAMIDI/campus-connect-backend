package com.campusconnect.backend.dto;

public class CreateClubRequest {

    private String name;
    private String faculty;
    private String clubAdminEmail;

    public CreateClubRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getClubAdminEmail() {
        return clubAdminEmail;
    }

    public void setClubAdminEmail(String clubAdminEmail) {
        this.clubAdminEmail = clubAdminEmail;
    }
}