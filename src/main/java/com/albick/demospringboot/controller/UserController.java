package com.albick.demospringboot.controller;

import com.albick.demospringboot.dto.LoginResponseDto;
import com.albick.demospringboot.dto.LoginUserDto;
import com.albick.demospringboot.dto.RegisterUserDto;
import com.albick.demospringboot.entity.User;
import com.albick.demospringboot.exception.RecordNotFoundException;
import com.albick.demospringboot.exception.RegistrationException;
import com.albick.demospringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/auth/register")
    public ResponseEntity<LoginResponseDto> register(@RequestBody RegisterUserDto registerUserDto) throws RegistrationException {
        userService.signup(registerUserDto);

        LoginUserDto loginUserDto = LoginUserDto.builder()
                .email(registerUserDto.getEmail())
                .password(registerUserDto.getPassword())
                .build();

        LoginResponseDto loginResponseDto = userService.login(loginUserDto);
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {
        LoginResponseDto loginResponseDto = userService.login(loginUserDto);
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<Void> logout() {
        userService.logout();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser() throws RecordNotFoundException {
        User currentUser = userService.getCurrentUser();
        return ResponseEntity.ok(currentUser);
    }
}
