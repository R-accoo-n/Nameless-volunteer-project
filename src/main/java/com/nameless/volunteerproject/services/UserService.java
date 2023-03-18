package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.dto.UserDto;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.repositories.UserRepository;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.management.relation.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;
import com.nameless.volunteerproject.enums.UserRole;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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

    public void saveUser(MultipartFile multipartFile, UserDto userDto){
        User user =new User();
        user.setSurname(userDto.getSurname());
        System.out.println(userDto.getSurname());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        System.out.println(userDto.getRole());
        user.setRole(userDto.getRole());
        if (user.getRole().name().equals("show-for-volunteer")||user.getRole().equals("show-for-military")) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setPhoto(Base64.getEncoder().encodeToString(fileName.getBytes()));
        }
        System.out.println(user);
        userRepository.save(user);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
