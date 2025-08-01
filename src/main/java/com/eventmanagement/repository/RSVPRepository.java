package com.eventmanagement.repository;

import com.eventmanagement.entity.RSVP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RSVPRepository extends JpaRepository<RSVP, Long> {
    Optional<RSVP> findByUserIdAndEventId(Long userId, Long eventId);
    Long countByEventIdAndStatus(Long eventId, RSVP.RSVPStatus status);
}
