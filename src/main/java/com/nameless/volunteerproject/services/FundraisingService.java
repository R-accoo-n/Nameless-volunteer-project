package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.repositories.FundraisingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundraisingService {
    private final FundraisingRepository fundraisingRepository;

    @Autowired
    public FundraisingService(
        FundraisingRepository fundraisingRepository) {
        this.fundraisingRepository = fundraisingRepository;
    }
}
