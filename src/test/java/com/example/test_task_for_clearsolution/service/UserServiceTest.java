package com.example.test_task_for_clearsolution.service;

import com.example.test_task_for_clearsolution.config.TestEntity;
import com.example.test_task_for_clearsolution.dto.UserFullDto;
import com.example.test_task_for_clearsolution.exeption.UserValidationException;
import com.example.test_task_for_clearsolution.mapper.UserMapperImpl;
import com.example.test_task_for_clearsolution.repository.UserStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    @Mock
    private UserStorage userStorage;

    @Mock
    private UserMapperImpl userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        var testUser = TestEntity.user;
        var testFullUserDto = TestEntity.userFullDto;
        var testUserDto = TestEntity.userDto;

        when(userStorage.getByEmail("test_toe@gmail.com")).thenReturn(testUser);
        when(userStorage.save(testUser)).thenReturn(testUser);
        when(userStorage.getByBirthDate(any(), any())).thenReturn(Collections.singletonList(testUser));

        when(userMapper.entityToDto(testUser)).thenReturn(testFullUserDto);
        when(userMapper.dtoToEntity(testFullUserDto)).thenReturn(testUser);
        when(userMapper.dtoToEntity(testUserDto)).thenReturn(testUser);
        when(userMapper.entityToDto(Collections.singletonList(testUser))).thenReturn(Collections.singletonList(testFullUserDto));
    }

    @Test
    void getUser() {
        var result = userService.getUser("test_toe@gmail.com");
        assertEquals(TestEntity.userFullDto, result, "User not equal");
    }

    @Test
    void delete() {
        when(userStorage.delete("test_toe@gmail.com"))
                .thenReturn(true);
        var result = userService.delete("test_toe@gmail.com");
        assertTrue(result);
    }

    @Test
    void register() {
        var testUserDto = TestEntity.userFullDto;

        var result = userService.register(testUserDto);
        assertEquals(TestEntity.userFullDto, result, "User not equal");
    }

    @Test
    void getByBirthDateException() {
        assertThrowsExactly(UserValidationException.class, () -> userService.getByBirthDate(LocalDate.now(), LocalDate.now().minusDays(1)));
    }

    @Test
    void getByBirthDate() {
        var userDtos = Collections.singletonList(TestEntity.userFullDto);
        var result = userService.getByBirthDate(LocalDate.now(), LocalDate.now());

        assertEquals(userDtos, result);
    }

    @Test
    void updateFullUser() {
        var testUserDto = TestEntity.userFullDto;

        userService.updateUser(testUserDto, testUserDto.getEmail());
        verify(userStorage).fullUpdate(any(), any());
    }

    @Test
    void updateFullUserException() {
        var testUserDto = UserFullDto.builder()
                .firstName("Test")
                .lastName("Toe")
                .address("Test")
                .birthDate(LocalDate.now().minusYears(17))
                .email("test_toe@gmail.com")
                .phoneNumber("0667492234")
                .build();

        assertThrowsExactly(UserValidationException.class, () -> userService.updateUser(testUserDto, testUserDto.getEmail()));
    }

    @Test
    void updateUser() {
        var testUserDto = TestEntity.userDto;

        userService.updateUser(testUserDto, "test_toe@gmail.com");
        verify(userStorage).update(any(), any());
    }
}