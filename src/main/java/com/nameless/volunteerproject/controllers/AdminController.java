package com.nameless.volunteerproject.controllers;
import com.nameless.volunteerproject.enums.FundraisingType;
import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.services.FundraisingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import com.nameless.volunteerproject.services.UserService;
import org.springframework.http.ResponseEntity;
import com.nameless.volunteerproject.enums.UserRole;



@RestController
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/admin/{adminId}/approve")
    public ResponseEntity<String> verifyUser(@PathVariable UUID userId, @RequestParam UserRole role) {
        boolean success = userService.verifyUser(userId, role);
        if (success) {
            return ResponseEntity.ok("User verified successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
