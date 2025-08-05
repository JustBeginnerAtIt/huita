package com.practice.userservice.controller;

import com.practice.userservice.dto.UserRequestDto;
import com.practice.userservice.dto.UserResponseDto;
import com.practice.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public void createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        userService.createUser(userRequestDto);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUser(@PathVariable("userId") Integer userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/all")
    public List<UserResponseDto> getUsers() {
        return userService.getAllUsers();
    }
}
