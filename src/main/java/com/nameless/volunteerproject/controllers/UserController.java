package com.nameless.volunteerproject.controllers;
import com.nameless.volunteerproject.models.Donations;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.repositories.DonationsRepository;
import com.nameless.volunteerproject.repositories.UserRepository;
import com.nameless.volunteerproject.services.UserService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository,
                          DonationsRepository donationsRepository) {
        this.userService = userService;
    }

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

}
