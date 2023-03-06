package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.repositories.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupportTicketService {
    private final SupportTicketRepository supportTicketRepository;

    @Autowired
    public SupportTicketService(
        SupportTicketRepository supportTicketRepository) {
        this.supportTicketRepository = supportTicketRepository;
    }
}
