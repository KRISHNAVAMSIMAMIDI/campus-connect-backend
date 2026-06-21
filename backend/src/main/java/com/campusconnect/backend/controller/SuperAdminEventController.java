package com.campusconnect.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.dto.SuperAdminEventResponse;
import com.campusconnect.backend.service.EventService;

@RestController
@RequestMapping("/api/admin/events")
@CrossOrigin(origins = "*")
public class SuperAdminEventController {

    private final EventService eventService;

    public SuperAdminEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<SuperAdminEventResponse> getAllEvents() {
        return eventService.getAllEventsForSuperAdmin();
    }

    @PutMapping("/{id}/approve")
    public SuperAdminEventResponse approveEvent(@PathVariable Long id) {
        return eventService.approveEvent(id);
    }

    @PutMapping("/{id}/reject")
    public SuperAdminEventResponse rejectEvent(@PathVariable Long id) {
        return eventService.rejectEvent(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEvent(@PathVariable Long id) {
        eventService.deleteEventForSuperAdmin(id);
        return ResponseEntity.ok(Map.of("message", "Event deleted successfully"));
    }
}
