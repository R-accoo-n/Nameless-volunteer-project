package com.nameless.volunteerproject.repositories;

import com.nameless.volunteerproject.models.FeedbackTicket;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for FeedbackTicket class
 */

@Repository
public interface FeedbackTicketRepository extends JpaRepository<FeedbackTicket, UUID> {

}
