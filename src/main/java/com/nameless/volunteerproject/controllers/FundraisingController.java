package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.dto.FundraisingDto;
import com.nameless.volunteerproject.enums.FundraisingType;
import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.repositories.FundraisingRepository;
import com.nameless.volunteerproject.repositories.UserRepository;
import com.nameless.volunteerproject.services.FundraisingService;
import com.nameless.volunteerproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class FundraisingController {
    private final FundraisingService fundraisingService;
    private final FundraisingRepository fundraisingRepository;
    private final UserService userService;

    private final UserRepository userRepository;

    @GetMapping("/fundraising/selected")
    public String selectedFudraising() {
        return "selectedFundraising";
    }

    @GetMapping("/activeFundraisingByType/{fundraisingType}")
    public String getActiveFundraisingsByType(@PathVariable FundraisingType type, Model model) {
        List<Fundraising> fundraisings = fundraisingService.getActiveFundraisingsByType(type);
        model.addAttribute("sortedFundraisings", fundraisings);
        return "redirect:/home";
    }

    @GetMapping("/profile")
    public List<Fundraising> getCompletedFundraisings(@PathVariable("userId") UUID userId) {
        return fundraisingService.getCompletedFundraisings(userId);
    }

    @GetMapping("/fundraising/{userId}")
    public String fundraisingForm(@PathVariable UUID userId, Model model) {
        FundraisingDto fundraisingDto = new FundraisingDto();
        model.addAttribute("fundraisingRequest", fundraisingDto);
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "fundraisingOfTheVolunteerOrMilitary";
    }

    @PostMapping("/fundraising/save/{userId}")
    public String createFundraising(@Valid @ModelAttribute("fundraisingRequest") FundraisingDto fundraisingDto, @PathVariable UUID userId, @RequestParam("image") MultipartFile multipartFile) {
        Fundraising fundraising = fundraisingService.createFundraising(fundraisingDto, multipartFile, userId);
        User user = userService.findUserById(userId);
        if (user.getRole().name().equals("MILITARY")) {
            return "redirect:/military/{userId}";
        } else {
            return "redirect:/volunteer/{userId}";
        }
    }

    @GetMapping("/statusFundraising")
    public List<Fundraising> getStatusFundraising() {
        return fundraisingRepository.findByIsActive(true);
    }

    @GetMapping("/fundraisingOverview/{fundraisingId}")
    public String fundraisingOverviewPage(@PathVariable UUID fundraisingId, Model model) {
        Fundraising fundraising = fundraisingService.findFundraisingById(fundraisingId);
        model.addAttribute("fundraising", fundraising);
        return "Fundraisingoverviewpage";
    }

    @GetMapping("/selected/fundraising/{userId}")
    public String selectedFundraising(@PathVariable UUID userId, Model model){
        return "selectedFundraising";
    }
}