package com.bogdanov.project.hospital_admission.utils.dto;

import com.bogdanov.project.hospital_admission.model.User;
import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    @NonNull
    private String email;

    private String password;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;

    private String role;

    private String status;

    public static UserDto fromUser(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                null,
                user.getFirstName(),
                user.getLastName(),
                user.getRole().name(),
                user.getStatus().name());
    }

}
