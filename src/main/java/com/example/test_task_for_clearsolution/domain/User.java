package com.example.test_task_for_clearsolution.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
}