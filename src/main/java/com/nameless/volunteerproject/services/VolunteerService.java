package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunteerService {
    private final UserRepository userRepository;

    @Autowired
    public VolunteerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
