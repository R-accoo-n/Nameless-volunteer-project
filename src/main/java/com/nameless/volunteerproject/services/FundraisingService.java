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
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FundraisingService {

    private final static String UPLOADED_FOLDER = "src/main/resources/static/images/uploadedImagesFundraising/";

    private final FundraisingRepository fundraisingRepository;
    private final FundraisingRequestRepository fundraisingRequestRepository;

    public List<Fundraising>findAllByActiveIsTrue(){return fundraisingRepository.findAllByIsActiveTrue();}

    public List<Fundraising> getActiveFundraisingsByType(FundraisingType type) {
        return fundraisingRepository.findBySocialTypeAndIsActive(type, true);
    }

    public List<Fundraising> getCompletedFundraisings(UUID userId) {
        return fundraisingRepository.findByUserIdAndIsActiveFalse(userId);
    }

    public void saveRequest(RequestDto requestDto, UUID userId) {
        FundraisingRequest fundraisingRequest = new FundraisingRequest();
        fundraisingRequest.setRequestName(requestDto.getName());
        fundraisingRequest.setWhom(requestDto.getWhom());
        fundraisingRequest.setGreyZone(requestDto.isGreyZone());
        fundraisingRequest.setDescription(requestDto.getDescription());
        fundraisingRequest.setMilitaryId(userId);
        fundraisingRequestRepository.save(fundraisingRequest);
    }

//    @GetMapping("/statusFundraisings(")
//    public List<Fundraising> getStatusFundraisings() {
//        return fundraisingRepository.findByIsActive(true);
//    }

    public List<Fundraising> getActiveFundraisings() {
        return fundraisingRepository.findByIsActive(true);
    }

    public Fundraising createFundraising(FundraisingDto fundraisingDto, MultipartFile filename, UUID userId) {
        fundraisingDto.setId(UUID.randomUUID());
        fundraisingDto.setActive(true);
        Fundraising fundraising = mapFundraisingDtoToFundraising(fundraisingDto, filename, userId);
        return fundraisingRepository.save(fundraising);
    }

    private Fundraising mapFundraisingDtoToFundraising(FundraisingDto fundraisingDto, MultipartFile multipartFile, UUID userId) {
        Fundraising fundraising = new Fundraising();
        fundraising.setId(fundraisingDto.getId());
        fundraising.setCardNumber(fundraisingDto.getCardNumber());
        fundraising.setDescription(fundraisingDto.getDescription());
        fundraising.setUserId(userId);
        saveImage(multipartFile);
        fundraising.setPhoto(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        fundraising.setFundraisingName(fundraisingDto.getFundraisingName());
        fundraising.setActive(fundraisingDto.isActive());
        fundraising.setSum(fundraisingDto.getSum());
        fundraising.setSocialType(fundraisingDto.getSocialType());
        fundraising.setWhom(fundraisingDto.getWhom());
        return fundraising;
    }

    // implement a method that saves an image file to a local directory and stores the file path in a database

    public void saveImage(MultipartFile file) {
        try {
            Path copyLocation = Paths
                    .get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

}