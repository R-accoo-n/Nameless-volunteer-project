package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonalController {

    private final UserService userService;

    public PersonalController(UserService userService) {
        this.userService = userService;}

    @GetMapping("/user")
    public String userPage(){
        return "userPersonalPage";
    }
}
