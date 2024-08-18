package com.albick.demospringboot.service;

import com.albick.demospringboot.entity.User;
import com.albick.demospringboot.exception.RecordNotFoundException;
import com.albick.demospringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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
