package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PersonalController {

    private final UserService userService;

    public PersonalController(UserService userService) {
        this.userService = userService;}

    @GetMapping("/user")
    public String userPage(@ModelAttribute("user") User user, Model model){
        System.out.println(user);
        return "userPersonalPage";
    }

    @GetMapping("/military")
    public String militaryPage(){
        return "militaryPersonalPage";
    }

    @GetMapping("/volunteer")
    public String volunteerPage(){
        return "volunteerPersonalPage";
    }


    @GetMapping("/donation/history")
    public String donationHistory(){
        return "donationHistory";
    }
}
