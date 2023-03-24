package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.models.FeedbackTicket;
import com.nameless.volunteerproject.repositories.FeedbackTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackTicketService {
    private final FeedbackTicketRepository feedbackTicketRepository;

    public FeedbackTicket createFeedbackTicket(FeedbackTicket feedbackTicket) {
        return feedbackTicketRepository.save(feedbackTicket);
    }
}
