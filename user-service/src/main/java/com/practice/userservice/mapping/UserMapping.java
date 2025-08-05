package com.practice.userservice.mapping;

import com.practice.userservice.dto.UserRequestDto;
import com.practice.userservice.dto.UserResponseDto;
import com.practice.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapping {
    public UserResponseDto mapToDto(User user) {

        if (user == null) {
            return null;
        }

        return UserResponseDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public User mapToEntity(UserRequestDto userDto) {

        if (userDto == null) {
            return null;
        }

        return User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .build();
    }
}
