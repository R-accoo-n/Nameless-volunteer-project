package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
        List<Fundraising> volunteersFundraising=userService.fundraisingsCreatedByVolunteer(user);
        model.addAttribute("fundraisingsCreatedByVolunteer",volunteersFundraising);
        System.out.println(volunteersFundraising.toString());
        return "volunteerPersonalPage";
    }

    @GetMapping("/donation/history")
    public String donationHistory(){
        return "donationHistory";
    }

    @PostMapping("/volunteer/photo/{userId}")
    public String editVolunteerPhoto(@PathVariable("userId") UUID userId, @RequestParam("image") MultipartFile multipartFile){
        userService.updateUser(userId, multipartFile);
        System.out.println("userId "+userId);
        return "redirect:/volunteer/{userId}?success";
    }
    @PostMapping("/military/photo/{userId}")
    public String editMilitaryPhoto(@PathVariable("userId") UUID userId, @RequestParam("image") MultipartFile multipartFile){
        userService.updateUser(userId, multipartFile);
        System.out.println("userId "+userId);
        return "redirect:/military/{userId}?success";
    }

    @PostMapping("/user/photo/{userId}")
    public String editUserPhoto(@PathVariable("userId") UUID userId, @RequestParam("image") MultipartFile multipartFile){
        userService.updateUser(userId, multipartFile);
        System.out.println("userId "+userId);
        return "redirect:/user/{userId}?success";
    }
}
