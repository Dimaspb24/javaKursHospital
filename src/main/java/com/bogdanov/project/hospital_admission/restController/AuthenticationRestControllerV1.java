package com.bogdanov.project.hospital_admission.restController;


import com.bogdanov.project.hospital_admission.model.User;
import com.bogdanov.project.hospital_admission.repository.UserRepository;
import com.bogdanov.project.hospital_admission.security.JwtTokenProvider;
import com.bogdanov.project.hospital_admission.services.UserServicesImpl;
import com.bogdanov.project.hospital_admission.utils.dto.AuthenticationRequestDto;
import com.bogdanov.project.hospital_admission.utils.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationRestControllerV1 {

    private final UserServicesImpl userService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationRestControllerV1(UserServicesImpl userService,
                                          AuthenticationManager authenticationManager,
                                          UserRepository userRepository,
                                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /*выдача токена*/
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDto request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
            String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name());
            HashMap<Object, Object> response = new HashMap<>();
            response.put("email", user.getEmail());
            response.put("userName", user.getFirstName() + " " + user.getLastName());
            response.put("role", user.getRole().name());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*регистрация*/
    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        if (userService.saveUser(userDto) != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.SEE_OTHER);
        }
    }
}
