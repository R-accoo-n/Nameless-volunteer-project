package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.dto.SupportDto;
import com.nameless.volunteerproject.dto.UserDto;
import com.nameless.volunteerproject.models.SupportTicket;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.nameless.volunteerproject.repositories.SupportTicketRepository;

import java.net.URI;

@Controller
public class SupportTicketController {

    private final SupportTicketRepository repository;

    public SupportTicketController(SupportTicketRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/support")
    public String support(Model model){
        SupportDto supportDto = new SupportDto();
        model.addAttribute("supportRequest", supportDto);
        return "support";
    }

    @PostMapping
    public ResponseEntity<SupportTicket> createSupportTicket(@RequestBody SupportTicket supportTicket) {
        SupportTicket createdTicket = repository.save(supportTicket);
        return ResponseEntity.created(URI.create("/support-tickets/" + createdTicket.getId())).body(createdTicket);
    }

}