package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.models.SupportTicket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nameless.volunteerproject.repositories.SupportTicketRepository;

import java.net.URI;

@RestController
@RequestMapping("/support-tickets")
public class SupportTicketController {

    private final SupportTicketRepository repository;

    public SupportTicketController(SupportTicketRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<SupportTicket> createSupportTicket(@RequestBody SupportTicket supportTicket) {
        SupportTicket createdTicket = repository.save(supportTicket);
        return ResponseEntity.created(URI.create("/support-tickets/" + createdTicket.getId())).body(createdTicket);
    }

}