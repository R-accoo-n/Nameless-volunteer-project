package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.models.SupportTicket;
import com.nameless.volunteerproject.repositories.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupportTicketService {
    private final SupportTicketRepository supportTicketRepository;

    @Autowired
    public SupportTicketService(
        SupportTicketRepository supportTicketRepository) {
        this.supportTicketRepository = supportTicketRepository;
    }
    public SupportTicket createSupportTicket(SupportTicket supportTicket) {
        return supportTicketRepository.save(supportTicket);
    }
    public Optional<SupportTicket> getSupportTicketById(UUID id) {
        return supportTicketRepository.findById(id);
    }

    public List<SupportTicket> getAllSupportTickets() {
        return supportTicketRepository.findAll();
    }
}
