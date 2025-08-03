package com.practice.userservice.serviceImpl;

import com.practice.userservice.dto.UserDto;
import com.practice.userservice.entity.User;
import com.practice.userservice.mapping.UserMapping;
import com.practice.userservice.repository.UserRepository;
import com.practice.userservice.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapping userMapping;

    @Override
    public void createUser(UserDto userDto) {
        userRepository.save(userMapping.mapToEntity(userDto));
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        Optional<UserDto> optUserDto = userRepository.findById(userId)
                .map(userMapping::mapToDto);
        return optUserDto
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> dtoList = userRepository.findAll()
                .stream()
                .map(userMapping::mapToDto)
                .toList();
        return dtoList;
    }
}
