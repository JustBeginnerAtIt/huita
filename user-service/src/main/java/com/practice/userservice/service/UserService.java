package com.practice.userservice.service;

import com.practice.userservice.dto.UserDto;

public interface UserService {
    void createUser(UserDto userDto);
    void deleteUser(Integer userId);
}
