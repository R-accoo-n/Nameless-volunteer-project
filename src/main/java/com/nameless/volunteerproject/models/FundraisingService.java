package com.nameless.volunteerproject.models;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.nameless.volunteerproject.enums.FundraisingType;
import com.nameless.volunteerproject.repositories.FundraisingRepository;

@Service
public class FundraisingService {

    @Autowired
    private FundraisingRepository fundraisingRepository;

    public List<Fundraising> getActiveFundraisingsByType(FundraisingType type) {
        return fundraisingRepository.findByTypeAndIsActive(type, true);
    }
}
