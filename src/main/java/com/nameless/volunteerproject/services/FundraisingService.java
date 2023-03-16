package com.nameless.volunteerproject.services;


import com.nameless.volunteerproject.dto.RequestDto;
import com.nameless.volunteerproject.dto.UserDto;
import com.nameless.volunteerproject.dto.FundraisingDto;
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


import java.util.List;
import java.util.UUID;

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

    public void saveRequest(RequestDto requestDto){
        FundraisingRequest fundraisingRequest=new FundraisingRequest();
        fundraisingRequest.setRequestName(requestDto.getName());
        fundraisingRequest.setWhom(requestDto.getWhom());
        fundraisingRequest.setGreyZone(requestDto.isGreyZone());
        fundraisingRequest.setDescription(requestDto.getDescription());
        fundraisingRequestRepository.save(fundraisingRequest);
    }

    public List<Fundraising> getActiveFundraisings() {
        return fundraisingRepository.findByIsActive(true);
    }

    public Fundraising createFundraising(FundraisingDto fundraisingDto) {
        fundraisingDto.setId(UUID.randomUUID());
        fundraisingDto.setActive(true);
        Fundraising fundraising = mapFundraisingDtoToFundraising(fundraisingDto);
        return fundraisingRepository.save(fundraising);
    }

    private Fundraising mapFundraisingDtoToFundraising(FundraisingDto fundraisingDto) {
        Fundraising fundraising = new Fundraising();
        // Map the properties from the FundraisingDto object to the Fundraising object
        fundraising.setId(fundraisingDto.getId());
        fundraising.setUserId(fundraisingDto.getUserId());
        fundraising.setCardNumber(fundraisingDto.getCardNumber());
        fundraising.setDescription(fundraisingDto.getDescription());
        fundraising.setFundraisingName(fundraisingDto.getFundraisingName());
        fundraising.setActive(fundraisingDto.isActive());
        fundraising.setSum(fundraisingDto.getSum());
        fundraising.setType(fundraisingDto.getType());
        fundraising.setWhom(fundraisingDto.getWhom());
        fundraising.setSelected(fundraisingDto.isSelected());
        // ...
        return fundraising;
    }
}
