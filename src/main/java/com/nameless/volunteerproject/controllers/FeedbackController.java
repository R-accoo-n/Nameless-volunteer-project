package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.dto.FeedbackDto;
import com.nameless.volunteerproject.models.FeedbackTicket;
import com.nameless.volunteerproject.services.FeedbackTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackTicketService feedbackTicketService;

    @PostMapping("/feedback")
    public ResponseEntity<FeedbackDto> createFeedbackTicket(@RequestBody FeedbackDto feedbackDto) {
        FeedbackTicket feedbackTicket = mapFeedBackDtoToFeedback(feedbackDto);
        FeedbackTicket savedFeedbackTicket = feedbackTicketService.createFeedbackTicket(feedbackTicket);
        FeedbackDto savedFeedbackDto = mapFeedBackToFeedbackDto(savedFeedbackTicket);
        return ResponseEntity.ok(savedFeedbackDto);
    }

    private FeedbackDto mapFeedBackToFeedbackDto(FeedbackTicket feedbackTicket) {
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setRate(feedbackTicket.getRate());
        feedbackDto.setDescription(feedbackTicket.getDescription());
        return feedbackDto;
    }

    private FeedbackTicket mapFeedBackDtoToFeedback(FeedbackDto feedbackDto) {
        FeedbackTicket feedbackTicket = new FeedbackTicket();
        feedbackTicket.setRate(feedbackDto.getRate());
        feedbackTicket.setDescription(feedbackDto.getDescription());
        return feedbackTicket;
    }

}