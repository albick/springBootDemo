package com.albick.demospringboot.mapper;

import com.albick.demospringboot.dto.UserDto;
import com.albick.demospringboot.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
}
