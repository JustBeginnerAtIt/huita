package com.practice.userservice.service;

import com.practice.userservice.dto.UserRequestDto;
import com.practice.userservice.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    void createUser(UserRequestDto userDto);
    void deleteUser(Integer userId);
    UserResponseDto getUserById(Integer userId);
    List<UserResponseDto> getAllUsers();
}
