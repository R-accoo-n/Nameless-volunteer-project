package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.repositories.AdminRepository;
import com.nameless.volunteerproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<User>findUsersByIsApprovedIsFalse(){
        return adminRepository.findUsersByIsApprovedIsFalse();
    }

    public List<User>findAll(){return adminRepository.findAll();}


}
