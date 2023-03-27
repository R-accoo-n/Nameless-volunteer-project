package com.nameless.volunteerproject.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginDto {
    private String email;
    private String password;
}
