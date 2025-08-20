package com.practice.userservice.serviceimpl;

import com.practice.userservice.dto.UserPagedResponse;
import com.practice.userservice.dto.UserRequestDto;
import com.practice.userservice.dto.UserResponseDto;
import com.practice.userservice.entity.User;
import com.practice.userservice.exception.UserNotFoundException;
import com.practice.userservice.mapping.UserMapping;
import com.practice.userservice.repository.UserRepository;
import com.practice.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapping userMapping;

    private User user;
    private UserRequestDto  userRequestDto;
    private UserResponseDto userResponseDto;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1)
                .username("testUser")
                .email("testUser@gmail.com")
                .role("ROLE_USER")
                .build();

        userRequestDto = UserRequestDto.builder()
                .username("testUser")
                .email("testUser@gmail.com")
                .build();

        userResponseDto = UserResponseDto.builder()
                .userId(1)
                .username("testUser")
                .email("testUser@gmail.com")
                .role("ROLE_USER")
                .build();
    }

    @Test
    void createUser_ShouldReturnUserResponseDto() {
        when(userMapping.mapToEntity(userRequestDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapping.mapToDto(user)).thenReturn(userResponseDto);

        UserResponseDto result = userService.createUser(userRequestDto);

        assertNotNull(result);
        assertEquals(userResponseDto, result);
        verify(userMapping).mapToEntity(userRequestDto);
        verify(userRepository).save(user);
        verify(userMapping).mapToDto(user);
    }

    @Test
    void deleteUser_WhenExists_ShouldDelete() {
        when(userRepository.existsById(1)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1);

        assertDoesNotThrow(() -> userService.deleteUser(1));
        verify(userRepository).existsById(1);
        verify(userRepository).deleteById(1);
    }

    @Test
    void deleteUser_WhenExists_ShouldThrow() {
        when(userRepository.existsById(1)).thenReturn(false);

        UserNotFoundException ex = assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1));
        assertTrue(ex.getMessage().contains("2"));
        verify(userRepository).existsById(2);
        verify(userRepository, never()).deleteById(any());
    }

    @Test
    void getUserById_WhenFound_ShouldReturnUserResponseDto() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userMapping.mapToDto(user)).thenReturn(userResponseDto);

        UserResponseDto result = userService.getUserById(1);

        assertEquals(userResponseDto, result);
        verify(userRepository).findById(1);
        verify(userMapping).mapToDto(user);
    }

    @Test
    void getUserById_WhenNotFound_ShouldThrow() {
        when(userRepository.findById(3)).thenReturn(Optional.empty());

        UserNotFoundException ex = assertThrows(UserNotFoundException.class, () -> userService.getUserById(3));

        assertTrue(ex.getMessage().contains("3"));
        verify(userRepository).findById(3);
    }

    @Test
    void getAllUsers_ShouldMapAll() {
        User secondUser = User.builder()
                .id(2)
                .username("testUser2")
                .email("testUser2@gmail.com")
                .role("ROLE_USER")
                .build();

        UserResponseDto responseDtoForSecondUser = UserResponseDto.builder()
                        .userId(2)
                                .username("testUser2")
                                        .email("testUser2@gmail.com")
                                                .role("ROLE_USER")
                                                        .build();

        when(userRepository.findAll()).thenReturn(List.of(user, secondUser));
        when(userMapping.mapToDto(user)).thenReturn(userResponseDto);
        when(userMapping.mapToDto(secondUser)).thenReturn(responseDtoForSecondUser);

        var allUsers = userService.getAllUsers();
        assertEquals(2, allUsers.size());
        assertEquals(userResponseDto, allUsers.get(0));
        assertEquals(responseDtoForSecondUser, allUsers.get(1));
        verify(userRepository).findAll();
        verify(userMapping).mapToDto(user);
        verify(userMapping).mapToDto(secondUser);
    }

    @Test
    void getAllUsersByPage_shouldReturnPagedResponse() {
        int page = 0, size = 2;
        User secondUser = User.builder().id(2).username("testUser2").email("testUser@gmail.com").role("ROLE_USER").build();

        List<User> content = List.of(user, secondUser);
        Page<User> userPage = new PageImpl<>(content, PageRequest.of(page, size), 10);

        when(userRepository.findAll(PageRequest.of(page, size))).thenReturn(userPage);
        when(userMapping.mapToDto(user)).thenReturn(userResponseDto);
        UserResponseDto dto2 = UserResponseDto.builder().userId(2).username("testUser2").email("testUser2@gmail.com").role("ROLE_USER").build();
        when(userMapping.mapToDto(secondUser)).thenReturn(dto2);

        UserPagedResponse<UserResponseDto> paged = userService.getAllUsersByPage(page, size);

        assertNotNull(paged);
        assertEquals(2, paged.data().size());
        assertEquals(0, paged.page());
        assertEquals(size, paged.size());
        verify(userRepository).findAll(PageRequest.of(page, size));
    }
}
