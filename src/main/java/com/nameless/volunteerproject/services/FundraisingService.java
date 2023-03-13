package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.dto.RequestDto;
import com.nameless.volunteerproject.dto.UserDto;
import com.nameless.volunteerproject.enums.FundraisingType;
import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.models.FundraisingRequest;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.repositories.FundraisingRepository;
import com.nameless.volunteerproject.repositories.FundraisingRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.UUID;


import java.util.List;

@Service
public class FundraisingService {
    private final FundraisingRepository fundraisingRepository;
    private final FundraisingRequestRepository fundraisingRequestRepository;

    @Autowired
    public FundraisingService(FundraisingRepository fundraisingRepository, FundraisingRequestRepository fundraisingRequestRepository) {
        this.fundraisingRepository = fundraisingRepository;
        this.fundraisingRequestRepository = fundraisingRequestRepository;
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

    public void saveRequest(RequestDto requestDto){
        FundraisingRequest fundraisingRequest=new FundraisingRequest();
        fundraisingRequest.setRequestName(requestDto.getName());
        fundraisingRequest.setWhom(requestDto.getWhom());
        fundraisingRequest.setGreyZone(requestDto.isGreyZone());
        fundraisingRequest.setDescription(requestDto.getDescription());
        fundraisingRequestRepository.save(fundraisingRequest);
    }



}
