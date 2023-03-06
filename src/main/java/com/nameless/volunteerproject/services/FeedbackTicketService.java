package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.repositories.FeedbackTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackTicketService {
    private final FeedbackTicketRepository feedbackTicketRepository;

    @Autowired
    public FeedbackTicketService(
        FeedbackTicketRepository feedbackTicketRepository) {
        this.feedbackTicketRepository = feedbackTicketRepository;
    }
}
