package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.enums.FundraisingType;
import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.repositories.FundraisingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;


import java.util.List;

@Service
public class FundraisingService {
    private final FundraisingRepository fundraisingRepository;

    @Autowired
    public FundraisingService(FundraisingRepository fundraisingRepository) {
        this.fundraisingRepository = fundraisingRepository;
    }
    public List<Fundraising> getActiveFundraisingsByType(FundraisingType type) {
        return fundraisingRepository.findByTypeAndIsActive(type, true);
    }
    public List<Fundraising> getCompletedFundraisings(UUID userId) {
        return fundraisingRepository.findByUserIdAndIsActiveFalse(userId);
    }
    public Fundraising createFundraising(Fundraising fundraising) {
        fundraising.setId(UUID.randomUUID());
        fundraising.setActive(true);
        return fundraisingRepository.save(fundraising);
    }



}
