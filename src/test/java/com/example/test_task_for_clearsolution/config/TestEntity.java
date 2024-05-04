package com.example.test_task_for_clearsolution.config;

import com.example.test_task_for_clearsolution.domain.User;
import com.example.test_task_for_clearsolution.dto.UserDto;
import com.example.test_task_for_clearsolution.dto.UserFullDto;

import java.time.LocalDate;

public class TestEntity {

    public static User user = User.builder()
            .firstName("Test")
            .lastName("Toe")
            .address("Test")
            .birthDate(LocalDate.now().minusYears(19))
            .email("test_toe@gmail.com")
            .phoneNumber("0667492234")
            .build();

    public static UserFullDto userFullDto = UserFullDto.builder()
            .firstName("Test")
            .lastName("Toe")
            .address("Test")
            .birthDate(LocalDate.now().minusYears(19))
            .email("test_toe@gmail.com")
            .phoneNumber("0667492234")
            .build();

    public static UserDto userDto = UserDto.builder()
            .firstName("Test")
            .lastName("Toe")
            .address("Test")
            .phoneNumber("0667492234")
            .build();

}
