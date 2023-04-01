package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.services.FundraisingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final FundraisingService fundraisingService;

    @GetMapping("/home")
    public String home(Model model){
        List<Fundraising> getAllActiveFundraising=fundraisingService.findAllByActiveIsTrue();
        model.addAttribute("allActiveFundraising", getAllActiveFundraising);
        return "index";
    }

    @GetMapping("/user/home")
    public String userHome(Model model){
        List<Fundraising> getAllActiveFundraising=fundraisingService.findAllByActiveIsTrue();
        model.addAttribute("allActiveFundraising", getAllActiveFundraising);
        return "userHome";
    }

    @GetMapping("/military/home")
    public String militaryHome(Model model){
        List<Fundraising> getAllActiveFundraising=fundraisingService.findAllByActiveIsTrue();
        model.addAttribute("allActiveFundraising", getAllActiveFundraising);
        return "militaryHome";
    }

    @GetMapping("/volunteer/home")
    public String volunteerHome(Model model){
        List<Fundraising> getAllActiveFundraising=fundraisingService.findAllByActiveIsTrue();
        model.addAttribute("allActiveFundraising", getAllActiveFundraising);
        return "volunteerHome";
    }
}
