package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.dto.RequestDto;
import com.nameless.volunteerproject.models.FundraisingRequest;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.repositories.FundraisingRequestRepository;
import com.nameless.volunteerproject.services.FundraisingService;
import com.nameless.volunteerproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class RequestController {
    private FundraisingService fundraisingService;
    private final UserService userService;
    private final FundraisingRequestRepository fundraisingRequestRepository;

    @GetMapping("/military/requests/{userId}")
    public  String request(@PathVariable UUID userId, Model model){
        List<FundraisingRequest>militaryRequests=fundraisingRequestRepository.findByMilitaryId(userId);
        model.addAttribute("militaryRequests", militaryRequests);
        User user=userService.findUserById(userId);
        model.addAttribute("user", user);
        return "myRequests";
    }

    @GetMapping("/request/{userId}")
    public String registrationForm(@PathVariable UUID userId, Model model){
        RequestDto requestDto = new RequestDto();
        System.out.println(requestDto.getName());
        User user=userService.findUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("militarysRequest", requestDto);
        return "militaryRequest";
    }

    @PostMapping("/request/save/{userId}")
    public String userRegistration(@Valid @ModelAttribute("militaryRequest") RequestDto requestDto, @PathVariable UUID userId, Model model){
        fundraisingService.saveRequest(requestDto, userId);
        return "redirect:/military/{userId}";
    }

    @GetMapping("/volunteer/requests/{userId}")
    public String requestsForVolunteer(@PathVariable UUID userId, Model model){
        List<FundraisingRequest>fundraisingRequests=fundraisingRequestRepository.findFundraisingRequestsByIsSatisfiedIsFalse();
        model.addAttribute("fundraisingRequests", fundraisingRequests);
        User user=userService.findUserById(userId);
        model.addAttribute("user", user);
        return "requestsForVolunteers";
    }

    @PostMapping("/volunteer/request/{requestId}/choose/{userId}")
    public String chooseRequestFromMilitary(@PathVariable UUID userId, @PathVariable UUID requestId){
        FundraisingRequest request=fundraisingRequestRepository.findFundraisingRequestById(requestId);
        if (request!=null) {
            request.setSatisfied(true);
            request.setUser(userService.findUserById(userId));
            fundraisingRequestRepository.save(request);
        }
        return "/volunteer/request/{requestId}/choose/{userId}?success";
    }

    @GetMapping("/volunteer/choosed/requests/{userId}")
    public String choosedRequestByVolunteer(@PathVariable UUID userId, Model model){
        List<FundraisingRequest>fundraisingRequests=fundraisingRequestRepository.findFundraisingRequestsByUserId(userId);
        model.addAttribute("fundraisingRequestsChoosedByVolunteer", fundraisingRequests);
        User user=userService.findUserById(userId);
        model.addAttribute("user", user);
        return "choosedRequestsForVolunteer";
    }


}
