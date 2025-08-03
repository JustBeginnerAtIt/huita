package com.practice.userservice.mapping;

import com.practice.userservice.dto.UserDto;
import com.practice.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapping {
    public UserDto mapToDto(User user) {

        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public mapToEntity(UserDto userDto) {

        if (userDto == null) {
            return null;
        }

        return User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .build();
    }
}
