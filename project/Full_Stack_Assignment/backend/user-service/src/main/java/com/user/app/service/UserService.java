package com.user.app.service;

import java.util.List;
import java.util.Optional;

import com.user.app.model.User;

public interface UserService {
    User createUser(User user) throws Exception;
    Optional<User> getUserById(Long userId) throws Exception;
    Optional<User> login(String emailId, String rawPassword) throws Exception;
    List<User> getAllUsers() throws Exception;
    User updateUser(Long userId, User user) throws Exception;
} 