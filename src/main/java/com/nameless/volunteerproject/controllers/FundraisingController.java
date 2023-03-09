package com.nameless.volunteerproject.controllers;
import com.nameless.volunteerproject.enums.FundraisingType;
import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.services.FundraisingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;



import java.util.List;

@RestController
public class FundraisingController {

    @Autowired
    private FundraisingService fundraisingService;

    @GetMapping("/home")
    public List<Fundraising> getActiveFundraisingsByType(@PathVariable FundraisingType type) {
        return fundraisingService.getActiveFundraisingsByType(type);
    }

    @PostMapping("/home")
    public void createFundraising(@RequestBody Fundraising fundraising) {
        fundraisingService.createFundraising(fundraising);
    }

    @GetMapping("/profile")
    public List<Fundraising> getCompletedFundraisings(@PathVariable("userId") UUID userId) {
        return fundraisingService.getCompletedFundraisings(userId);
    }

}