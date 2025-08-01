package com.eventmanagement.service;

import com.eventmanagement.dto.EventDTO;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.User;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Cacheable(value = "publicEvents")
    public Page<EventDTO> getPublicEvents(Pageable pageable) {
        return eventRepository.findPublishedEvents(pageable)
                .map(this::convertToDTO);
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = Event.builder()
                .title(eventDTO.getTitle())
                .description(eventDTO.getDescription())
                .startDate(eventDTO.getStartDate())
                .endDate(eventDTO.getEndDate())
                .location(eventDTO.getLocation())
                .capacity(eventDTO.getCapacity())
                .price(eventDTO.getPrice())
                .status(Event.EventStatus.DRAFT)
                .user(user)
                .build();

        Event savedEvent = eventRepository.save(event);
        return convertToDTO(savedEvent);
    }

    @CacheEvict(value = "publicEvents", allEntries = true)
    public EventDTO updateEvent(Long id, EventDTO eventDTO) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setStartDate(eventDTO.getStartDate());
        event.setEndDate(eventDTO.getEndDate());
        event.setLocation(eventDTO.getLocation());
        event.setCapacity(eventDTO.getCapacity());
        event.setPrice(eventDTO.getPrice());

        Event savedEvent = eventRepository.save(event);
        return convertToDTO(savedEvent);
    }

    @Cacheable(value = "eventCapacity", key = "#eventId")
    public Long getEventCapacity(Long eventId) {
        return eventRepository.countAttendeesByEventId(eventId);
    }

    private EventDTO convertToDTO(Event event) {
        return EventDTO.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .location(event.getLocation())
                .capacity(event.getCapacity())
                .price(event.getPrice())
                .status(event.getStatus().name())
                .createdAt(event.getCreatedAt())
                .build();
    }
}
