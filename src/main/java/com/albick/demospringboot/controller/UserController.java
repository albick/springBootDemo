package com.albick.demospringboot.controller;

import com.albick.demospringboot.entity.User;
import com.albick.demospringboot.exception.RecordNotFoundException;
import com.albick.demospringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() throws RecordNotFoundException {
        return new ResponseEntity<>(userService.getCurrentUser(), HttpStatus.OK);
    }
}
