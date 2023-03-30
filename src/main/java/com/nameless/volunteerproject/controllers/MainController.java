package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.repositories.UserRepository;
import com.nameless.volunteerproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @GetMapping("/home")
    public String home(){
        return "index";
    }

    @GetMapping("/user/home")
    public String userHome(){
        return "userHome";
    }

    @GetMapping("/military/home")
    public String militaryHome(){
        return "militaryHome";
    }

    @GetMapping("/volunteer/home")
    public String volunteerHome(){
        return "volunteerHome";
    }
}
