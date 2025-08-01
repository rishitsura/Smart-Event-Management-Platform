package com.eventmanagement.repository;

import com.eventmanagement.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    
    @Query("SELECT e FROM Event e WHERE e.status = 'PUBLISHED' ORDER BY e.startDate ASC")
    Page<Event> findPublishedEvents(Pageable pageable);
    
    List<Event> findByUserIdOrderByStartDateAsc(Long userId);
    
    @Query("SELECT COUNT(r) FROM RSVP r WHERE r.event.id = :eventId AND r.status = 'ATTENDING'")
    Long countAttendeesByEventId(Long eventId);
}
