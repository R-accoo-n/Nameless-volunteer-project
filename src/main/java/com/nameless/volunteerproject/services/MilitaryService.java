package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MilitaryService {
    private  final UserRepository userRepository;

    @Autowired
    public MilitaryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email){
        return this.userRepository.findByEmail(email).orElseThrow();
    }
}
