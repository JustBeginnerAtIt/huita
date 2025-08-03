package com.practice.userservice.service;

import com.practice.userservice.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void createUser(UserDto userDto);
    void deleteUser(Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
}
