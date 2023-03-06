package com.nameless.volunteerproject.repositories;

import com.nameless.volunteerproject.models.SupportTicket;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for SupportTicket
 */

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, UUID> {

}
