package com.albick.demospringboot.dto;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String email;
    private String fullName;
    private String aud;
}
