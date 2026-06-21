package com.campusconnect.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campusconnect.backend.dto.SuperAdminEventResponse;
import com.campusconnect.backend.entity.Event;
import com.campusconnect.backend.exception.ResourceNotFoundException;
import com.campusconnect.backend.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public Event addEvent(Event event) {
        return repository.save(event);
    }

    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    public Event getEventById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }

    public List<SuperAdminEventResponse> getAllEventsForSuperAdmin() {
        return repository.findAll()
                .stream()
                .map(this::toSuperAdminResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public SuperAdminEventResponse approveEvent(Long id) {
        Event event = findEvent(id);
        event.setStatus("APPROVED");
        return toSuperAdminResponse(repository.save(event));
    }

    @Transactional
    public SuperAdminEventResponse rejectEvent(Long id) {
        Event event = findEvent(id);
        event.setStatus("REJECTED");
        return toSuperAdminResponse(repository.save(event));
    }

    @Transactional
    public void deleteEventForSuperAdmin(Long id) {
        findEvent(id);
        repository.deleteById(id);
    }

    private Event findEvent(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    private SuperAdminEventResponse toSuperAdminResponse(Event event) {
        return new SuperAdminEventResponse(
                event.getId(),
                event.getEventName(),
                event.getOrganizer(),
                event.getEventDate(),
                event.getStatus());
    }
    public List<Event> getEventsByClubId(Long clubId) {
    return repository.findByClubId(clubId);
}
}
