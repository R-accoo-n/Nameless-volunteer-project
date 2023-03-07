package com.nameless.volunteerproject;
import com.nameless.volunteerproject.enums.FundraisingType;
import com.nameless.volunteerproject.models.Fundraising;
import com.nameless.volunteerproject.repositories.FundraisingRepository;
import com.nameless.volunteerproject.services.*;
//import com.nameless.volunteerproject.controllers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;

@SpringBootApplication
public class VolunteerProjectApplication {




	public static void main(String[] args) {
		SpringApplication.run(VolunteerProjectApplication.class, args);

	}

}
