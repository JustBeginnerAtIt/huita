package com.practice.userservice.controller;

import com.practice.userservice.dto.UserRequestDto;
import com.practice.userservice.dto.UserResponseDto;
import com.practice.userservice.exception.ApiResponse;
import com.practice.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserResponseDto>> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto createdUser = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.getSuccessResponse("User created successfully", createdUser));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(ApiResponse.getSuccessResponse("User deleted successfully", null));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUser(@PathVariable("userId") Integer userId) {
        UserResponseDto gotUser = userService.getUserById(userId);
        return ResponseEntity.ok(ApiResponse.getSuccessResponse("User found successfully", gotUser));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.getSuccessResponse("Users found successfully", users));
    }
}
