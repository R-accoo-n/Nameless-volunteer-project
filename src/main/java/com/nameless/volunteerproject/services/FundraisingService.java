package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.enums.FundraisingType;
import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.repositories.FundraisingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundraisingService {
    private final FundraisingRepository fundraisingRepository;

    @Autowired
    public FundraisingService(
        FundraisingRepository fundraisingRepository) {
        this.fundraisingRepository = fundraisingRepository;
    }
    public List<Fundraising> getActiveFundraisingsByType(FundraisingType type) {
        return fundraisingRepository.findByTypeAndIsActive(type, true);
    }
}
