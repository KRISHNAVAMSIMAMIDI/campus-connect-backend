package com.campusconnect.backend.dto;

public class AdminDashboardResponse {

    private long totalStudents;
    private long totalClubs;
    private long totalEvents;
    private long totalClubAdmins;
    private long totalAnnouncements;
    private long totalApplications;

    public AdminDashboardResponse() {
    }

    public AdminDashboardResponse(
            long totalStudents,
            long totalClubs,
            long totalEvents,
            long totalClubAdmins,
            long totalAnnouncements,
            long totalApplications) {
        this.totalStudents = totalStudents;
        this.totalClubs = totalClubs;
        this.totalEvents = totalEvents;
        this.totalClubAdmins = totalClubAdmins;
        this.totalAnnouncements = totalAnnouncements;
        this.totalApplications = totalApplications;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public long getTotalClubs() {
        return totalClubs;
    }

    public void setTotalClubs(long totalClubs) {
        this.totalClubs = totalClubs;
    }

    public long getTotalEvents() {
        return totalEvents;
    }

    public void setTotalEvents(long totalEvents) {
        this.totalEvents = totalEvents;
    }

    public long getTotalClubAdmins() {
        return totalClubAdmins;
    }

    public void setTotalClubAdmins(long totalClubAdmins) {
        this.totalClubAdmins = totalClubAdmins;
    }

    public long getTotalAnnouncements() {
        return totalAnnouncements;
    }

    public void setTotalAnnouncements(long totalAnnouncements) {
        this.totalAnnouncements = totalAnnouncements;
    }

    public long getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(long totalApplications) {
        this.totalApplications = totalApplications;
    }
}
