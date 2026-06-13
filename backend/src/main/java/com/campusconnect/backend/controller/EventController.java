package com.campusconnect.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.entity.Event;
import com.campusconnect.backend.service.EventService;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
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