package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.dto.UserDto;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import com.nameless.volunteerproject.enums.UserRole;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }

//    public void saveUser(User user);
//    public List<Object> isUserPresent(User user);
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

    public void saveUser(UserDto userDto){
        User user =new User();
        user.setSurname(userDto.getSurname());
        System.out.println(userDto.getSurname());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(UserRole.USER);
        System.out.println(user);
        userRepository.save(user);

    }
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
