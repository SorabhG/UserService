package com.example.UserService.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id) {
        super("Could Not find User - " + id);
    }
}
