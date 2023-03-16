package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.dto.RequestDto;
import com.nameless.volunteerproject.dto.UserDto;
import com.nameless.volunteerproject.dto.FundraisingDto;
import com.nameless.volunteerproject.enums.FundraisingType;
import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.repositories.FundraisingRepository;
import com.nameless.volunteerproject.services.FundraisingService;
import com.nameless.volunteerproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FundraisingController {

    private FundraisingService fundraisingService;
    @Autowired
    private final FundraisingService fundraisingService;
    private final FundraisingRepository fundraisingRepository;


    @GetMapping("/activeFundraising")
    public List<Fundraising> getActiveFundraisingsByType(@PathVariable FundraisingType type) {
        return fundraisingService.getActiveFundraisingsByType(type);
    }

    @PostMapping("/createFundraising")
    public void createFundraising(@RequestBody FundraisingDto fundraisingDto) {
        fundraisingService.createFundraising(fundraisingDto);
    }

    @GetMapping("/profile")
    public List<Fundraising> getCompletedFundraisings(@PathVariable("userId") UUID userId) {
        return fundraisingService.getCompletedFundraisings(userId);
    }

    @GetMapping("/statusFundraisings(")
    public List<Fundraising> getStatusFundraisings() {
        return fundraisingRepository.findByIsActive(true);
    }

}
