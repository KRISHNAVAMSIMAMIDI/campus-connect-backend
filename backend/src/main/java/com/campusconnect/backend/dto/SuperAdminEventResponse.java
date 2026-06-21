package com.campusconnect.backend.dto;

public class SuperAdminEventResponse {

    private Long eventId;
    private String eventName;
    private String clubName;
    private String eventDate;
    private String status;

    public SuperAdminEventResponse() {
    }

    public SuperAdminEventResponse(Long eventId, String eventName, String clubName, String eventDate, String status) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.clubName = clubName;
        this.eventDate = eventDate;
        this.status = status;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
