package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class PersonalController {

    private final UserService userService;

    public PersonalController(UserService userService) {
        this.userService = userService;}

    @GetMapping("/user/{userId}")
    public String userPage(@PathVariable UUID userId, Model model){
        User user=userService.findUserById(userId);
        model.addAttribute("user", user);
    return "userPersonalPage";
    }

    @GetMapping("/military/{userId}")
    public String militaryPage(@PathVariable UUID userId, Model model){
        User user=userService.findUserById(userId);
        model.addAttribute("user", user);
        return "militaryPersonalPage";
    }

    @GetMapping("/volunteer/{userId}")
    public String volunteerPage(@PathVariable UUID userId, Model model){
        User user=userService.findUserById(userId);
        model.addAttribute("user", user);
        return "volunteerPersonalPage";
    }


    @GetMapping("/donation/history")
    public String donationHistory(){
        return "donationHistory";
    }
}
