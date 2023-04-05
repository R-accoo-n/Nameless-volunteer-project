package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.services.FundraisingService;
import com.nameless.volunteerproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final FundraisingService fundraisingService;

    private final UserService userService;

    @GetMapping("/home")
    public String home(Model model){
        List<Fundraising> getAllActiveFundraising=fundraisingService.findAllByActiveIsTrue();
        model.addAttribute("allActiveFundraising", getAllActiveFundraising);
        return "index";
    }

    @GetMapping("/user/home/{userId}")
    public String userHome(@PathVariable UUID userId, Model model){
        List<Fundraising> getAllActiveFundraising=fundraisingService.findAllByActiveIsTrue();
        model.addAttribute("allActiveFundraising", getAllActiveFundraising);
        User user=userService.findUserById(userId);
        model.addAttribute("user", user);
        return "userHome";
    }

    @GetMapping("/military/home/{userId}")
    public String militaryHome(@PathVariable UUID userId, Model model){
        List<Fundraising> getAllActiveFundraising=fundraisingService.findAllByActiveIsTrue();
        model.addAttribute("allActiveFundraising", getAllActiveFundraising);
        User user=userService.findUserById(userId);
        model.addAttribute("user", user);
        return "militaryHome";
    }

    @GetMapping("/volunteer/home/{userId}")
    public String volunteerHome(@PathVariable UUID userId, Model model){
        List<Fundraising> getAllActiveFundraising=fundraisingService.findAllByActiveIsTrue();
        model.addAttribute("allActiveFundraising", getAllActiveFundraising);
        User user=userService.findUserById(userId);
        model.addAttribute("user", user);
        return "volunteerHome";
    }
}
