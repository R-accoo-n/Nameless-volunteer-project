package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.dto.UserDto;
import com.nameless.volunteerproject.enums.UserRole;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.services.UserService;
import java.net.PasswordAuthentication;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.lang.reflect.Array;
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
        List<UserRole> roles =
                new ArrayList<UserRole>(EnumSet.allOf(UserRole.class));
        for (UserRole role:roles) {
            System.out.println(role);
        }
        model.addAttribute("roles", roles);
        return "registration";
    }

    @PostMapping("/register/save")
    public String userRegistration(@Valid @ModelAttribute("user") UserDto userDto, @RequestParam("image")MultipartFile multipartFile, BindingResult result, Model model){
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
        userService.saveUser(multipartFile, userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String login()
    {
        User user=getPrincipal();
        if (user!=null){
            return "authentication";
        }
        return "login";
    }

    private User getPrincipal(){
        User user=null;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User){
            user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return user;
    }

    @PostMapping("/login/authorisation")
    public String userLogin(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model) {
        Optional<User> existingUser = userService.findUserByEmail(email);
        System.out.println(email);
        System.out.println(password);
        if(existingUser.isEmpty()){
            throw new UsernameNotFoundException("User with this email doesn't exist");
        }else if(existingUser.get().getPassword() != password){
            throw new UsernameNotFoundException("Wrong password");
        }
        return "redirect:/home";
    }

}