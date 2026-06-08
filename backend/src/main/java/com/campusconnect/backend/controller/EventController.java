package com.campusconnect.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.campusconnect.backend.entity.Event;
import com.campusconnect.backend.service.EventService;

@RestController
@RequestMapping("/api/events")
@CrossOrigin("*")
public class EventController {

    @Autowired
    private EventService service;

    @PostMapping
    public Event addEvent(
            @RequestBody Event event) {

        return service.addEvent(event);
    }

    @GetMapping
    public List<Event> getAllEvents() {

        return service.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEvent(
            @PathVariable Long id) {

        return service.getEventById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(
            @PathVariable Long id) {

        service.deleteEvent(id);

        return "Event Deleted Successfully";
    }
}