package com.practice.userservice.controller;

import com.practice.userservice.dto.UserDto;
import com.practice.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable("userId") Integer userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/all")
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }
}
