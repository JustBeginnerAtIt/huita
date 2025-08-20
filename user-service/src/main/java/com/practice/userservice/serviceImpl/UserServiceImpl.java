package com.practice.userservice.serviceImpl;

import com.practice.userservice.dto.UserPagedResponse;
import com.practice.userservice.dto.UserRequestDto;
import com.practice.userservice.dto.UserResponseDto;
import com.practice.userservice.entity.User;
import com.practice.userservice.exception.UserNotFoundException;
import com.practice.userservice.mapping.UserMapping;
import com.practice.userservice.repository.UserRepository;
import com.practice.userservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//TODO: доделать логирование, исправить недочеты по уровням логирования

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapping userMapping;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        log.debug("Mapping user entity from UserRequestDto...");
        var entityUser = userMapping.mapToEntity(userRequestDto);
        log.info("User entity with username {} successfully mapped from UserRequestDto", entityUser.getUsername());
        log.debug("Saving user entity to DB...");
        var savedUser = userRepository.save(entityUser);
        log.info("User entity with username {} successfully saved in DB", savedUser.getUsername());
        log.debug("Mapping and Returning user Entity to UserResponseDto...");
        return userMapping.mapToDto(savedUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        log.debug("Checking if user exists with id: {}", userId);
        if(!userRepository.existsById(userId)) {
            log.error("User does not exist with id: {}", userId);
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        log.info("User found with id: {}", userId);
        log.info("Deleting user with id: {}", userId);
        userRepository.deleteById(userId);
        log.info("User deleted with id: {}", userId);
    }

    @Override
    public UserResponseDto getUserById(Integer userId) {
        log.debug("Searching user with id: {}", userId);
        Optional<UserResponseDto> optUserDto = userRepository.findById(userId)
                .map(userMapping::mapToDto);
        log.info("User found with id: {} and mapped to DTO", userId);
        return optUserDto
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        log.debug("Searching all users from DB and pushing them to list...");
        return userRepository.findAll()
                .stream()
                .map(userMapping::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserPagedResponse<UserResponseDto> getAllUsersByPage(Integer page, Integer size) {
        log.debug("Making pagination with parameters of the method");
        Pageable pageable = PageRequest.of(page, size);
        log.debug("Making user page through searching all users in DB");
        Page<User> userPage = userRepository.findAll(pageable);

        log.debug("Making mapped list of users through stream API and mapping it to user response DTO");
        List<UserResponseDto> users = userPage.getContent()
                .stream()
                .map(userMapping::mapToDto)
                .toList();
        log.info("Made list of users: {}", users);

        log.debug("Returning paged response of made list");
        return UserPagedResponse.of(
                users,
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                "Users retrieved successfully"
        );
    }
}
