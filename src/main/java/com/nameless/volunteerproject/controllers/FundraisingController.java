package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.dto.FundraisingDto;
import com.nameless.volunteerproject.enums.FundraisingType;
import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.repositories.FundraisingRepository;
import com.nameless.volunteerproject.services.FundraisingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/fundraising")
    public ResponseEntity<Fundraising> createFundraising(@RequestBody FundraisingDto fundraisingDto) {
        Fundraising fundraising = fundraisingService.createFundraising(fundraisingDto);
        return ResponseEntity.ok().body(fundraising);
    }

    @GetMapping("/statusFundraising")
    public List<Fundraising> getStatusFundraising() {
        return fundraisingRepository.findByIsActive(true);
    }

}