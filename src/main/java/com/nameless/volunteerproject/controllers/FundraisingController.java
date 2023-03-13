package com.nameless.volunteerproject.controllers;
import com.nameless.volunteerproject.dto.RequestDto;
import com.nameless.volunteerproject.dto.UserDto;
import com.nameless.volunteerproject.enums.FundraisingType;
import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.services.FundraisingService;
import com.nameless.volunteerproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.UUID;



import java.util.List;

@Controller
public class FundraisingController {
    private FundraisingService fundraisingService;
    public FundraisingController(FundraisingService fundraisingService) {
        this.fundraisingService = fundraisingService;}

    @GetMapping("/activeFundraising")
    public List<Fundraising> getActiveFundraisingsByType(@PathVariable FundraisingType type) {
        return fundraisingService.getActiveFundraisingsByType(type);
    }

    @PostMapping("/createFundraising")
    public void createFundraising(@RequestBody Fundraising fundraising) {
        fundraisingService.createFundraising(fundraising);
    }

    @GetMapping("/profile")
    public List<Fundraising> getCompletedFundraisings(@PathVariable("userId") UUID userId) {
        return fundraisingService.getCompletedFundraisings(userId);
    }


}