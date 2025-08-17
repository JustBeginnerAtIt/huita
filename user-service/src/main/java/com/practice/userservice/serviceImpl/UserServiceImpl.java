package com.practice.userservice.serviceImpl;

import com.practice.userservice.dto.UserRequestDto;
import com.practice.userservice.dto.UserResponseDto;
import com.practice.userservice.exception.UserNotFoundException;
import com.practice.userservice.mapping.UserMapping;
import com.practice.userservice.repository.UserRepository;
import com.practice.userservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapping userMapping;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        var entityUser = userMapping.mapToEntity(userRequestDto);
        var savedUser = userRepository.save(entityUser);
        return userMapping.mapToDto(savedUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        if(!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public UserResponseDto getUserById(Integer userId) {
        Optional<UserResponseDto> optUserDto = userRepository.findById(userId)
                .map(userMapping::mapToDto);
        return optUserDto
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapping::mapToDto)
                .collect(Collectors.toList());
    }
}
