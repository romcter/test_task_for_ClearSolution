package com.example.test_task_for_clearsolution.controller;

import com.example.test_task_for_clearsolution.dto.UserDto;
import com.example.test_task_for_clearsolution.dto.UserFullDto;
import com.example.test_task_for_clearsolution.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<UserFullDto> getUser(@PathVariable String email){
        return ResponseEntity.ok(
                userService.getUser(email)
        );
    }

    @GetMapping("birthDate/{from}/{to}")
    @Operation(description = "Search user by birth date range")
    public ResponseEntity<List<UserFullDto>> getUser(
            @Parameter(example = "2000-05-02") @PathVariable LocalDate from,
            @Parameter(example = "2005-05-02") @PathVariable LocalDate to
    ){
        return ResponseEntity.ok(
                userService.getByBirthDate(from, to)
        );
    }

    @PostMapping
    public ResponseEntity<UserFullDto> registerUser(@RequestBody @Valid UserFullDto userDto){
        return ResponseEntity.ok(
                userService.register(userDto)
        );
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<UserFullDto> deleteUser(@PathVariable String email){
        userService.delete(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserFullDto> putUser(@RequestBody @Valid UserFullDto userDto, @PathVariable String email) {
        userService.updateUser(userDto, email);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{email}")
    public ResponseEntity<UserFullDto> patchUser(@RequestBody @Valid UserDto userDto, @PathVariable String email){
        userService.updateUser(userDto, email);
        return ResponseEntity.ok().build();
    }
}
