package com.albick.demospringboot.service;

import com.albick.demospringboot.dto.LoginResponseDto;
import com.albick.demospringboot.dto.LoginUserDto;
import com.albick.demospringboot.dto.RegisterUserDto;
import com.albick.demospringboot.dto.UserDto;
import com.albick.demospringboot.entity.User;
import com.albick.demospringboot.exception.RecordNotFoundException;
import com.albick.demospringboot.exception.RegistrationException;
import com.albick.demospringboot.mapper.UserMapper;
import com.albick.demospringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;

    public void signup(RegisterUserDto input) throws RegistrationException {
        User user = User.builder()
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();
        boolean userExists = userRepository.findByEmail(input.getEmail()).isPresent();

        if (userExists) {
            throw new RegistrationException("User already registered");
        }
        userRepository.save(user);
    }

    public LoginResponseDto login(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        User authenticatedUser= userRepository.findByEmail(input.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(authenticatedUser);
        long expiresAt=(Instant.now().toEpochMilli() +jwtService.getExpirationTime())/1000;
        UserDto authenticatedUserDto=userMapper.toUserDto(authenticatedUser);
        authenticatedUserDto.setAud("authenticated");
        return LoginResponseDto.builder()
                .token(jwtToken)
                .expiresAt(expiresAt)
                .user(authenticatedUserDto)
                .build();
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

    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
