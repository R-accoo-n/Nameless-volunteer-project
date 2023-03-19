package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.dto.FundraisingDto;
import com.nameless.volunteerproject.enums.FundraisingType;
import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.repositories.FundraisingRepository;
import com.nameless.volunteerproject.services.FundraisingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class FundraisingController {
    private final FundraisingService fundraisingService;
    private final FundraisingRepository fundraisingRepository;

    @GetMapping("/activeFundraising")
    public List<Fundraising> getActiveFundraisingsByType(@PathVariable FundraisingType type) {
        return fundraisingService.getActiveFundraisingsByType(type);
    }

    @GetMapping("/profile")
    public List<Fundraising> getCompletedFundraisings(@PathVariable("userId") UUID userId) {
        return fundraisingService.getCompletedFundraisings(userId);
    }

    @GetMapping("/fundraising")
    public String fundraisingForm(Model model){
        FundraisingDto fundraisingDto=new FundraisingDto();
        model.addAttribute("fundraisingRequest", fundraisingDto);
        return "fundraisingOfTheVolunteerOrMilitary";
    }
    @PostMapping("/fundraising/save")
    public String createFundraising(@Valid @ModelAttribute("fundraisingRequest") FundraisingDto fundraisingDto) {
        Fundraising fundraising = fundraisingService.createFundraising(fundraisingDto);
        return "redirect:/fundraising?success";
    }

    @GetMapping("/statusFundraising")
    public List<Fundraising> getStatusFundraising() {
        return fundraisingRepository.findByIsActive(true);
    }

}