package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.dto.RequestDto;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.services.FundraisingService;
import com.nameless.volunteerproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class RequestController {
    private FundraisingService fundraisingService;
    private final UserService userService;

    @Autowired
    public RequestController(FundraisingService fundraisingService, UserService userService) {
        this.fundraisingService = fundraisingService;
        this.userService = userService;
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
}
