package com.bogdanov.project.hospital_admission.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}