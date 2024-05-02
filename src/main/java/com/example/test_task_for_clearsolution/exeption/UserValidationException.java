package com.example.test_task_for_clearsolution.exeption;

public class UserValidationException extends RuntimeException {
    public UserValidationException(String message){
        super(message);
    }
}
