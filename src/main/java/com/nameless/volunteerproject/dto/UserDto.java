package com.nameless.volunteerproject.dto;


import com.nameless.volunteerproject.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private String surname;

    private String name;

    private String email;

    private String phoneNumber;

    private String password;

    private UserRole role;

    private String photo;

    private String socialMedia;

    private boolean isApproved;
}
