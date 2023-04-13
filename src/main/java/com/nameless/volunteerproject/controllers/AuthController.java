package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.dto.LoginDto;
import com.nameless.volunteerproject.dto.UserDto;
import com.nameless.volunteerproject.models.User;
import com.nameless.volunteerproject.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;}

    @GetMapping("/militaryRegistration")
    public String militaryRegistration(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registrationFormForMilitary";
    }

    @GetMapping("/userRegistration")
    public String userRegistration(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registrationFormForUser";
    }

    @GetMapping("/volunteerRegistration")
    public String volunteerRegistration(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registrationFormVolunteer";
    }

    @GetMapping("/register")
    public String registrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "chooseYourRole";
    }

    @PostMapping("/register/save/user")
    public String userRegistration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model){
//        if (userService.emailExists(userDto.getEmail())) {
//            result.rejectValue("email", null,
//                    "There is already an account registered with the same email");
//            return "redirect:/register";
//        }else {
            userService.saveUser(userDto);
            return "redirect:/login";
        //}
    }
    @PostMapping("/register/save/volunteer")
    public String volunteerRegistration(@Valid @ModelAttribute("user") UserDto userDto, @RequestParam("image")MultipartFile multipartFile, BindingResult result, Model model){
//        if (userService.emailExists(userDto.getEmail())) {
//            result.rejectValue("email", null,
//                    "There is already an account registered with the same email");
//            ResponseEntity.ok("There is already an account registered with the same email");
//            return "redirect:/register";
//        }else {
            userService.saveVolunteer(multipartFile, userDto);
            return "redirect:/login";
        //}
    }

    @PostMapping("/register/save/military")
    public String militaryRegistration(@Valid @ModelAttribute("user") UserDto userDto, @RequestParam("image")MultipartFile multipartFile, BindingResult result, Model model){
//        if (userService.emailExists(userDto.getEmail())) {
//            result.rejectValue("email", null,
//                    "There is already an account registered with the same email");
//            return "redirect:/register";
//        }else {
            userService.saveMilitary(multipartFile, userDto);
            return "redirect:/login";
        //}
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
        model.addAttribute("user", user);
        System.out.println("ver "+model.getAttribute("user"));
        System.out.println(userValue);
        if (userValue==true) {
            if (user.isBlocked()){
                return "redirect:/blocked";
            }
            else if(user.getRole().name().equals("MILITARY")&&user.isApproved()){
                return "redirect:/military/home/"+user.getId();
            }else if (user.getRole().name().equals("VOLUNTEER")&&user.isApproved()){
                return "redirect:/volunteer/home/"+user.getId();
            }
            else if((user.getRole().name().equals("MILITARY")&&!user.isApproved())||user.getRole().name().equals("VOLUNTEER")&&!user.isApproved()){
                return "redirect:/waiting";
            }
            else{
                return "redirect:/user/home/"+user.getId();
            }
        }else{
            return "redirect:/login";
        }
    }


}