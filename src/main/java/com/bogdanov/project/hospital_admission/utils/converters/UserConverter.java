package com.bogdanov.project.hospital_admission.utils.converters;

import com.bogdanov.project.hospital_admission.model.User;
import com.bogdanov.project.hospital_admission.model.enums.Role;
import com.bogdanov.project.hospital_admission.model.enums.Status;
import com.bogdanov.project.hospital_admission.repository.UserRepository;
import com.bogdanov.project.hospital_admission.utils.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserConverter {

    private static UserRepository userRepository;
    private static PasswordEncoder passwordEncoder;

    public UserConverter(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        UserConverter.userRepository = userRepository;
        UserConverter.passwordEncoder = passwordEncoder;
    }

    // для пользовательской страницы
    public static User toUserCurrent(UserDto userDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> optionalUser = userRepository.findByEmail(auth.getName());

//        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isEmpty()) return null;

        User user = optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return user;
    }

    // для админской страницы
    public static User toUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isEmpty()) return null;

        User user = optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setStatus(Status.valueOf(userDto.getStatus()));
        user.setRole(Role.valueOf(userDto.getRole()));
        return user;
    }

    public static User toNewUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setStatus(Status.valueOf(userDto.getStatus()));
        user.setRole(Role.valueOf(userDto.getRole()));
        user.setEmail(userDto.getEmail());
        return user;
    }


    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().name(),
                user.getStatus().name()
        );
    }
}
