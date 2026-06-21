package com.campusconnect.backend.dto;

public class SuperAdminAnalyticsResponse {

    private long students;
    private long clubs;
    private long events;
    private long clubAdmins;
    private long approvedEvents;
    private long pendingEvents;

    public SuperAdminAnalyticsResponse() {
    }

    public SuperAdminAnalyticsResponse(
            long students,
            long clubs,
            long events,
            long clubAdmins,
            long approvedEvents,
            long pendingEvents) {
        this.students = students;
        this.clubs = clubs;
        this.events = events;
        this.clubAdmins = clubAdmins;
        this.approvedEvents = approvedEvents;
        this.pendingEvents = pendingEvents;
    }

    public long getStudents() {
        return students;
    }

    public void setStudents(long students) {
        this.students = students;
    }

    public long getClubs() {
        return clubs;
    }

    public void setClubs(long clubs) {
        this.clubs = clubs;
    }

    public long getEvents() {
        return events;
    }

    public void setEvents(long events) {
        this.events = events;
    }

    public long getClubAdmins() {
        return clubAdmins;
    }

    public void setClubAdmins(long clubAdmins) {
        this.clubAdmins = clubAdmins;
    }

    public long getApprovedEvents() {
        return approvedEvents;
    }

    public void setApprovedEvents(long approvedEvents) {
        this.approvedEvents = approvedEvents;
    }

    public long getPendingEvents() {
        return pendingEvents;
    }

    public void setPendingEvents(long pendingEvents) {
        this.pendingEvents = pendingEvents;
    }
}
