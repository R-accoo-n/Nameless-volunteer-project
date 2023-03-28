package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.dto.FeedbackDto;
import com.nameless.volunteerproject.dto.UserDto;
import com.nameless.volunteerproject.models.FeedbackTicket;
import com.nameless.volunteerproject.services.FeedbackTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackTicketService feedbackTicketService;

    @GetMapping("/feedback")
    public String feedback(Model model){
        FeedbackDto feedbackDto = new FeedbackDto();
        model.addAttribute("feedback", feedbackDto);
        return "response";
    }
    @PostMapping("/feedback/save")
    public String createFeedbackTicket(@Valid @ModelAttribute("feedback") FeedbackDto feedbackDto) {
        FeedbackTicket feedbackTicket = mapFeedBackDtoToFeedback(feedbackDto);
        FeedbackTicket savedFeedbackTicket = feedbackTicketService.createFeedbackTicket(feedbackTicket);
        FeedbackDto savedFeedbackDto = mapFeedBackToFeedbackDto(savedFeedbackTicket);
        return "redirect:/feedback?success";
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