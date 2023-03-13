package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.dto.UserDto;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/register/save")
    public String userRegistration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model){
//        User existingUser = userService.findUserByEmail(userDto.getEmail()).get();
//
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
//        }
        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String login(Model model){
        User user=new User();
        model.addAttribute("user", user);
        return "login";
    }

}
