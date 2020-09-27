package com.bogdanov.project.hospital_admission.services;

import com.bogdanov.project.hospital_admission.model.User;
import com.bogdanov.project.hospital_admission.model.enums.Role;
import com.bogdanov.project.hospital_admission.model.enums.Status;
import com.bogdanov.project.hospital_admission.repository.UserRepository;
import com.bogdanov.project.hospital_admission.utils.converters.UserConverter;
import com.bogdanov.project.hospital_admission.utils.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public UserServicesImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            return null;
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        return userRepository.save(user);
    }

    public List<UserDto> getUsers() {
        List<UserDto> users = userRepository.findAll()
                .stream()
                .map(UserConverter::toUserDto)
                .collect(Collectors.toList());
        return users;
    }

//    public UserDto getUserById(Long id) {
//        User user = userRepository.getOne(id);
//        return UserConverter.toUserDto(user);
//    }

    public UserDto getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return UserConverter.toUserDto(user.get());
    }

    public UserDto saveOrUpdateUser(UserDto userDto) {
        Optional<User> foundUser = userRepository.findByEmail(userDto.getEmail());
        if (foundUser.isEmpty()) {
            User userToSave = UserConverter.toNewUser(userDto);
            User thatUser = userRepository.save(userToSave);
            return UserConverter.toUserDto(thatUser);
        } else {
            User userBd = foundUser.get();

            String newPassword = userDto.getPassword();
            if (newPassword != null && !newPassword.equals("")) {
                userBd.setPassword(passwordEncoder.encode(newPassword));
            }
            userBd.setFirstName(userDto.getFirstName());
            userBd.setLastName(userDto.getLastName());
            userBd.setRole(Role.valueOf(userDto.getRole()));
//            userBd.setStatus(Status.valueOf(userDto.getStatus()));

            User saved = userRepository.save(userBd);
            return UserConverter.toUserDto(saved);
        }
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
