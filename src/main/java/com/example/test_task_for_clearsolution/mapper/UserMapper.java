package com.example.test_task_for_clearsolution.mapper;

import com.example.test_task_for_clearsolution.domain.User;
import com.example.test_task_for_clearsolution.dto.UserDto;
import com.example.test_task_for_clearsolution.dto.UserFullDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface UserMapper {
    User dtoToEntity(UserFullDto dto);
    User dtoToEntity(UserDto dto);
    UserFullDto entityToDto(User entity);
    List<UserFullDto> entityToDto(List<User> entity);
}
