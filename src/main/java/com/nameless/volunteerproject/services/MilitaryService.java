package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MilitaryService {
    private final UserRepository userRepository;
}
