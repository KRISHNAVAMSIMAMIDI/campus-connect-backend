package com.campusconnect.backend.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.campusconnect.backend.dto.SuperAdminAnalyticsResponse;

@Service
public class SuperAdminAnalyticsService {

    private final JdbcTemplate jdbcTemplate;

    public SuperAdminAnalyticsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public SuperAdminAnalyticsResponse getAnalytics() {
        return new SuperAdminAnalyticsResponse(
                countUsersByRole("STUDENT"),
                countRows("clubs"),
                countRows("events"),
                countUsersByRole("CLUB_ADMIN"),
                countEventsByStatus("APPROVED"),
                countEventsByStatus("PENDING"));
    }

    private long countRows(String tableName) {
        Long count = jdbcTemplate.queryForObject("select count(*) from " + tableName, Long.class);
        return count == null ? 0 : count;
    }

    private long countUsersByRole(String role) {
        Long count = jdbcTemplate.queryForObject(
                """
                select count(*) from users
                where role = ?
                   or role like concat(?, ',%')
                   or role like concat('%,', ?)
                   or role like concat(concat('%,', ?), ',%')
                """,
                Long.class,
                role,
                role,
                role,
                role);

        return count == null ? 0 : count;
    }

    private long countEventsByStatus(String status) {
        Long count = jdbcTemplate.queryForObject(
                "select count(*) from events where upper(status) = ?",
                Long.class,
                status);

        return count == null ? 0 : count;
    }
}
