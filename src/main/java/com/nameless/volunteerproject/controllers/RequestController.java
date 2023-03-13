package com.nameless.volunteerproject.controllers;

import com.nameless.volunteerproject.dto.RequestDto;
import com.nameless.volunteerproject.services.FundraisingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
public class RequestController {
    private FundraisingService fundraisingService;

    public RequestController(FundraisingService fundraisingService) {
        this.fundraisingService = fundraisingService;}

    //ADD MILITARY ID

    @GetMapping("/request")
    public String registrationForm(Model model){
        RequestDto requestDto = new RequestDto();
        System.out.println(requestDto.getName());
        model.addAttribute("militarysRequest", requestDto);
        return "militaryRequest";
    }

    @PostMapping("/request/save")
    public String userRegistration(@Valid @ModelAttribute("militaryRequest") RequestDto requestDto, Model model){
        fundraisingService.saveRequest(requestDto);
        return "redirect:/request?success";
    }
}
