package com.nameless.volunteerproject.controllers;
import com.nameless.volunteerproject.enums.UserRole;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import com.nameless.volunteerproject.services.UserService;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final AdminService adminService;

    @Autowired
    public AdminController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @GetMapping
    public String admin(Model model){
        List<User> notApprovedUsers=adminService.findUsersByIsApprovedIsFalse();
        model.addAttribute("notApprovedUsers", notApprovedUsers);
        for (User user:notApprovedUsers){
            System.out.println(user);
        }
        return "adminPage";
    }

    @GetMapping("/users")
    public String usersForAdmin(Model model){
        List<User>users=adminService.findAll();
        model.addAttribute("usersForAdmin", users);
        return "adminpage_allusers";
    }


    @PostMapping("/approve/{userId}")
    public ResponseEntity<String> approveUser(@PathVariable UUID userId) {
        boolean success = userService.approveUser(userId);
        if (success) {
            return ResponseEntity.ok("User approved successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/block/{userId}")
    public ResponseEntity<String> blockUser(@PathVariable UUID userId) {
        boolean success = userService.blockUser(userId);
        if (success) {
            return ResponseEntity.ok("User blocked successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
