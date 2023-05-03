package com.nameless.volunteerproject.controllers;
import com.nameless.volunteerproject.models.Donations;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.repositories.DonationsRepository;
import com.nameless.volunteerproject.repositories.UserRepository;
import com.nameless.volunteerproject.services.UserService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/user/{userId}/history")
    public List<Donations> getAllDonationsByUserId(@PathVariable UUID userId){
        return userService.findDonationHistory(userId);
    }

    @GetMapping("/military/volunteers/{userId}")
    public String allVolunteers(@PathVariable UUID userId, Model model){
        User user=userService.findUserById(userId);
        model.addAttribute("user", user);
        List<User>volunteers=userService.findAllVolunteers();
        model.addAttribute("volunteers", volunteers);
        return "allVolunteers";
    }

    @PostMapping("/user/edit/{userId}")
    public String editUser(@PathVariable("userId") UUID userId, @ModelAttribute("user")User user){
        User userPresentedInDB = userRepository.findUserById(userId);
        userPresentedInDB.setId(userId);
        userPresentedInDB.setSurname(user.getSurname());
        userPresentedInDB.setName(user.getName());
        userPresentedInDB.setEmail(user.getEmail());
        userPresentedInDB.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(userPresentedInDB);
        System.out.println("user"+user);
        return "redirect:/user/{userId}?success";
    }

    @PostMapping("/volunteer/edit/{userId}")
    public String editVolunteer(@PathVariable("userId") UUID userId, @ModelAttribute("user")User user){
        User userPresentedInDB = userRepository.findUserById(userId);
        userPresentedInDB.setId(userId);
        userPresentedInDB.setUserName(user.getUserName());
        userPresentedInDB.setSurname(user.getSurname());
        userPresentedInDB.setName(user.getName());
        userPresentedInDB.setEmail(user.getEmail());
        userPresentedInDB.setPhoneNumber(user.getPhoneNumber());
        userPresentedInDB.setDescription(user.getDescription());
        userRepository.save(userPresentedInDB);
        System.out.println("user"+user);
        return "redirect:/volunteer/{userId}?success";
    }

    @PostMapping("/military/edit/{userId}")
    public String editMilitary(@PathVariable("userId") UUID userId, @ModelAttribute("user")User user){
        User userPresentedInDB = userRepository.findUserById(userId);
        userPresentedInDB.setId(userId);
        userPresentedInDB.setUserName(user.getUserName());
        userPresentedInDB.setSurname(user.getSurname());
        userPresentedInDB.setName(user.getName());
        userPresentedInDB.setEmail(user.getEmail());
        userPresentedInDB.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(userPresentedInDB);
        System.out.println("user"+user);
        return "redirect:/military/{userId}?success";
    }


}
