package com.albick.demospringboot.service;

import com.albick.demospringboot.dto.LoginUserDto;
import com.albick.demospringboot.dto.RegisterUserDto;
import com.albick.demospringboot.entity.User;
import com.albick.demospringboot.exception.RecordNotFoundException;
import com.albick.demospringboot.exception.RegistrationException;
import com.albick.demospringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public User signup(RegisterUserDto input) throws RegistrationException {
        User user = User.builder()
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();
        boolean userExists = userRepository.findByEmail(input.getEmail()).isPresent();

        if (userExists) {
            throw new RegistrationException("User already registered");
        }
        return userRepository.save(user);
    }

    public User login(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

    public User getCurrentUser() throws RecordNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var currentPrincipal = (User) authentication.getPrincipal();
        var userResult = userRepository.findByEmail(currentPrincipal.getEmail());
        if (userResult.isEmpty()) {
            throw new RecordNotFoundException(User.class.getSimpleName());
        }
        return userResult.get();
    }
}
