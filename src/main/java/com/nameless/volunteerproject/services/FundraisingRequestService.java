package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.repositories.FundraisingRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundraisingRequestService {
    private final FundraisingRequestRepository fundraisingRequestRepository;

    @Autowired
    public FundraisingRequestService(
        FundraisingRequestRepository fundraisingRequestRepository) {
        this.fundraisingRequestRepository = fundraisingRequestRepository;
    }
}
