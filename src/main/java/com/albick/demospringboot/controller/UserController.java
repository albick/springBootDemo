package com.albick.demospringboot.controller;

import com.albick.demospringboot.controller.response.LoginResponse;
import com.albick.demospringboot.dto.LoginUserDto;
import com.albick.demospringboot.dto.RegisterUserDto;
import com.albick.demospringboot.entity.User;
import com.albick.demospringboot.exception.RegistrationException;
import com.albick.demospringboot.service.UserService;
import com.albick.demospringboot.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final JWTService jwtService;

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) throws RegistrationException {
        User registeredUser = userService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = userService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}
