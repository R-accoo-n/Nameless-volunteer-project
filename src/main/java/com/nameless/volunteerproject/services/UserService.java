package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.configuration.ApiResponse;
import com.nameless.volunteerproject.dto.LoginDto;
import com.nameless.volunteerproject.dto.UserDto;
import com.nameless.volunteerproject.models.Donations;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.repositories.DonationsRepository;
import com.nameless.volunteerproject.repositories.UserRepository;

import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import com.nameless.volunteerproject.enums.UserRole;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final DonationsRepository donationsRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       DonationsRepository donationsRepository) {
        this.userRepository = userRepository;
        this.donationsRepository = donationsRepository;
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

    public boolean approveUser(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setApproved(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean blockUser(UUID userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setBlocked(true);
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
        user.setPassword(userDto.getPassword());
        user.setApproved(userDto.isApproved());
        System.out.println(userDto.getRole());
        user.setRole(userDto.getRole());
        if(user.getRole().name().equals("USER")){
            user.setApproved(true);
        }
        if(user.getRole().name().equals("VOLUNTEER")){
            user.setSocialMedia(userDto.getSocialMedia());
        }
        if (user.getRole().name().equals("VOLUNTEER")||user.getRole().name().equals("MILITARY")) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setPhoto(Base64.getEncoder().encodeToString(fileName.getBytes()));
        }
        System.out.println(user);
        userRepository.save(user);
    }

    public boolean login(LoginDto loginDto, Model model){
        //validation
        System.out.println(loginDto.getEmail());
        System.out.println(loginDto.getPassword());
        //verify user exist
        User user=userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        System.out.println(user);
        model.addAttribute("user", user);
        //response
        if(user!=null){
            System.out.println("User with this email exist");
            return true;
        }
        return false;
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<Donations> findDonationHistory(UUID userId){
        return donationsRepository.findAllByUserID(userId);
    }

}
