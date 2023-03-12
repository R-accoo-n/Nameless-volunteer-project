package com.nameless.volunteerproject.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

public class VolunteerDto {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private String surname;

    private String name;

    private String fatherName;

    private String email;

    private String phoneNumber;

    private String password;
}
