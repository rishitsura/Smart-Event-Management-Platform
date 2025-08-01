package com.eventmanagement.controller;

import com.eventmanagement.dto.ApiResponse;
import com.eventmanagement.dto.EventDTO;
import com.eventmanagement.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    private final EventService eventService;

    @GetMapping("/public")
    public ResponseEntity<Page<EventDTO>> getPublicEvents(Pageable pageable) {
        return ResponseEntity.ok(eventService.getPublicEvents(pageable));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponse<EventDTO>> createEvent(@Valid @RequestBody EventDTO eventDTO) {
        EventDTO created = eventService.createEvent(eventDTO);
        return ResponseEntity.ok(ApiResponse.success("Event created successfully", created));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<EventDTO>> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventDTO eventDTO) {
        EventDTO updated = eventService.updateEvent(id, eventDTO);
        return ResponseEntity.ok(ApiResponse.success("Event updated successfully", updated));
    }

    @GetMapping("/{id}/capacity")
    public ResponseEntity<ApiResponse<Long>> getEventCapacity(@PathVariable Long id) {
        Long capacity = eventService.getEventCapacity(id);
        return ResponseEntity.ok(ApiResponse.success("Event capacity retrieved", capacity));
    }
}
