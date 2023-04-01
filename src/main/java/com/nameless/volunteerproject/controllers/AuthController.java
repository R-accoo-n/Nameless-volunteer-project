package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.configuration.ApiResponse;
import com.nameless.volunteerproject.dto.LoginDto;
import com.nameless.volunteerproject.dto.UserDto;
import com.nameless.volunteerproject.enums.UserRole;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;}

    @GetMapping("/register")
    public String registrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/register/save")
    public String userRegistration(@Valid @ModelAttribute("user") UserDto userDto, @RequestParam("image")MultipartFile multipartFile, BindingResult result, Model model){
 //       User existingUser = userService.findUserByEmail(userDto.getEmail()).get();

//        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
//            result.rejectValue("email", null,
//                    "There is already an account registered with the same email");
//        }
//
//        if(result.hasErrors()){
//            result.rejectValue("email", null,
//                    "There is already an account registered with the same email");
////            model.addAttribute("user", userDto);
////            return "/register";
 //       }
        userService.saveUser(multipartFile, userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String login(Model model){
        LoginDto user = new LoginDto();
        model.addAttribute("loginUser", user);
        return "login";
    }

    @GetMapping("/waiting")
    public String waiting(Model model){
        User user= (User) model.getAttribute("user");
        return "waiting";
    }

    @GetMapping("/blocked")
    public String blocked(Model model){
        User user= (User) model.getAttribute("user");
        return "pageForBlockedUser";
    }

    @PostMapping("/login/in")
    public String loginPost(@Valid @ModelAttribute("loginUser") LoginDto loginDto, Model model){
        boolean userValue=userService.login(loginDto, model);
        User user= (User) model.getAttribute("user");
        System.out.println("ouruser "+user);
        System.out.println(userValue);
        System.out.println(user.getRole().name().equals("MILITARY"));
        if (userValue==true) {
            if (user.isBlocked()){
                return "redirect:/blocked";
            }
            else if(user.getRole().name().equals("MILITARY")&&user.isApproved()){
                return "redirect:/military/home";
            }else if (user.getRole().name().equals("VOLUNTEER")&&user.isApproved()){
                return "redirect:/volunteer/home";
            }
            else if((user.getRole().name().equals("MILITARY")&&!user.isApproved())||user.getRole().name().equals("VOLUNTEER")&&!user.isApproved()){
                return "redirect:/waiting";
            }
            else{
                return "redirect:/user/home";
            }
        }else{
            return "redirect:/login";
        }
    }


}