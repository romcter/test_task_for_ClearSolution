package com.example.test_task_for_clearsolution.service;

import com.example.test_task_for_clearsolution.dto.UserDto;
import com.example.test_task_for_clearsolution.dto.UserFullDto;
import com.example.test_task_for_clearsolution.exeption.UserValidationException;
import com.example.test_task_for_clearsolution.mapper.UserMapper;
import com.example.test_task_for_clearsolution.repository.UserStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserStorage userStorage;
    private UserMapper userMapper;

    public UserFullDto getUser(String email) {
        return userMapper.entityToDto(userStorage.getByEmail(email));
    }

    public boolean delete(String email) {
        return userStorage.delete(email);
    }

    public UserFullDto register(UserFullDto userDto) {
        validateAge(userDto);

        return userMapper.entityToDto(
                userStorage.save(
                        userMapper.dtoToEntity(userDto)));
    }

    public List<UserFullDto> getByBirthDate(LocalDate from, LocalDate to) {
        validateDateRange(from, to);
        return userMapper.entityToDto(userStorage.getByBirthDate(from, to));
    }

    public void updateUser(UserFullDto userDto, String email){
        validateAge(userDto);

        userStorage.fullUpdate(userMapper.dtoToEntity(userDto), email);
    }

    public void updateUser(UserDto userDto, String email){
        userStorage.update(userMapper.dtoToEntity(userDto), email);
    }

    private void validateAge(UserFullDto userDto){
        if (LocalDate.now().minusYears(18).isBefore(userDto.getBirthDate()))
            throw new UserValidationException("User age should be more than 18.");
    }

    private void validateDateRange(LocalDate from, LocalDate to){
        if (from.isAfter(to))
            throw new UserValidationException(String.format("FROM {%s} be earlier than TO {%s} date", from, to));
    }
}
