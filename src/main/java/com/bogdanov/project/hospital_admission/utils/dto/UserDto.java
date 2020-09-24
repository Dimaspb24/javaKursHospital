package com.bogdanov.project.hospital_admission.utils.dto;

import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;

    private String role;

    private String status;

}
