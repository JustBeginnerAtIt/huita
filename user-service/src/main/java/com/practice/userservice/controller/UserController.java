package com.practice.userservice.controller;

import com.practice.userservice.dto.UserDto;
import com.practice.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Integer userId) {
        userService.deleteUser(userId);
    }
}
