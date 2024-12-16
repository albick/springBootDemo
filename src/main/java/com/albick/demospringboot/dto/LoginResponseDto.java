package com.albick.demospringboot.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String token;
    private long expiresAt;
    private UserDto user;
}
