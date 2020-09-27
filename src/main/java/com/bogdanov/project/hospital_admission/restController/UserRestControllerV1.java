package com.bogdanov.project.hospital_admission.restController;

import com.bogdanov.project.hospital_admission.services.UserServicesImpl;
import com.bogdanov.project.hospital_admission.utils.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestControllerV1 {

    private final UserServicesImpl userServices;

    public UserRestControllerV1(UserServicesImpl userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userServices.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//    @GetMapping("{id}")
//    UserDto getUserById(@PathVariable Long id ) {
//        return userServices.getUserById(id);
//    }

    @GetMapping("{email}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        UserDto user = userServices.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        UserDto user = userServices.saveOrUpdateUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userServices.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
