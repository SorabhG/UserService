package com.example.UserService.service;

import com.example.UserService.dao.User;

import java.util.List;

public interface UserService {
    User addUser(final User user);

    List<User> getAllUsers();

    User getUser(final String id);
}
