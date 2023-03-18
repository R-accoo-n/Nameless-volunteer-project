package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.dto.FundraisingDto;
import com.nameless.volunteerproject.dto.RequestDto;
import com.nameless.volunteerproject.enums.FundraisingType;
import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.models.FundraisingRequest;
import com.nameless.volunteerproject.repositories.FundraisingRepository;
import com.nameless.volunteerproject.repositories.FundraisingRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FundraisingService {

    private final FundraisingRepository fundraisingRepository;
    private final FundraisingRequestRepository fundraisingRequestRepository;

    public List<Fundraising> getActiveFundraisingsByType(FundraisingType type) {
        return fundraisingRepository.findByTypeAndIsActive(type, true);
    }

    public List<Fundraising> getCompletedFundraisings(UUID userId) {
        return fundraisingRepository.findByUserIdAndIsActiveFalse(userId);
    }

    public void saveRequest(RequestDto requestDto) {
        FundraisingRequest fundraisingRequest = new FundraisingRequest();
        fundraisingRequest.setRequestName(requestDto.getName());
        fundraisingRequest.setWhom(requestDto.getWhom());
        fundraisingRequest.setGreyZone(requestDto.isGreyZone());
        fundraisingRequest.setDescription(requestDto.getDescription());
        fundraisingRequestRepository.save(fundraisingRequest);
    }

    @GetMapping("/statusFundraisings(")
    public List<Fundraising> getStatusFundraisings() {
        return fundraisingRepository.findByIsActive(true);
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
        return fundraising;
    }
}