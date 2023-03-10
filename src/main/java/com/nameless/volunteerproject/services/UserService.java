package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nameless.volunteerproject.models.User;
import java.util.UUID;
import com.nameless.volunteerproject.enums.UserRole;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean verifyUser(UUID userId, UserRole role) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setRole(role);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
