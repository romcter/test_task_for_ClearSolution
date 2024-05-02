package com.example.test_task_for_clearsolution.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFullDto {
    @NotNull
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "validation for email failed")
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Past
    @NotNull
    private LocalDate birthDate;

    private String address;
    private String phoneNumber;
}
