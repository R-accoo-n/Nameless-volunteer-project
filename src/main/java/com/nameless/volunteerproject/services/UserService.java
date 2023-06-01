package com.nameless.volunteerproject.services;

import com.nameless.volunteerproject.dto.LoginDto;
import com.nameless.volunteerproject.dto.UserDto;
import com.nameless.volunteerproject.enums.UserRole;
import com.nameless.volunteerproject.models.Donations;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.repositories.DonationsRepository;
import com.nameless.volunteerproject.repositories.UserRepository;
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
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final DonationsRepository donationsRepository;

    private final static String UPLOADED_FOLDER = "src/main/resources/static/images/uploadedImagesUsers/";


    public List<User>findAllVolunteers(){
        return userRepository.findUserByRole(UserRole.VOLUNTEER);
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

    public void saveUser(UserDto userDto){
        User user =new User();
        user.setSurname(userDto.getSurname());
        System.out.println(userDto.getSurname());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setApproved(true);
        user.setRole(UserRole.USER);
//        System.out.println(userDto.getRole());
//        user.setRole(userDto.getRole());
//        if(user.getRole().name().equals("USER")){
//            user.setApproved(true);
//        }
//        if(user.getRole().name().equals("VOLUNTEER")){
//            user.setSocialMedia(userDto.getSocialMedia());
//        }
//        if (user.getRole().name().equals("VOLUNTEER")||user.getRole().name().equals("MILITARY")) {
//            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//            user.setPhoto(Base64.getEncoder().encodeToString(fileName.getBytes()));
//        }
//        System.out.println(user);
        userRepository.save(user);
    }

    public void saveVolunteer(MultipartFile multipartFile, UserDto userDto){
        User user =new User();
        user.setSurname(userDto.getSurname());
        System.out.println(userDto.getSurname());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setSocialMedia(userDto.getSocialMedia());
        user.setPassword(userDto.getPassword());
        user.setApproved(userDto.isApproved());
        System.out.println(userDto.getRole());
        user.setRole(UserRole.VOLUNTEER);
        saveImage(multipartFile);
        user.setPhoto(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        System.out.println(user);
        userRepository.save(user);
    }

    public boolean emailExists(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user != null;
    }

    public void saveMilitary(MultipartFile multipartFile, UserDto userDto){
        User user =new User();
        user.setSurname(userDto.getSurname());
        System.out.println(userDto.getSurname());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setApproved(userDto.isApproved());
        System.out.println(userDto.getRole());
        user.setRole(UserRole.MILITARY);
        saveImage(multipartFile);
        user.setPhoto(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
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

    public User findUserById(UUID id) {
        return userRepository.findUserById(id);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<Donations> findDonationHistory(UUID userId){
        return donationsRepository.findAllByUserID(userId);
    }

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
