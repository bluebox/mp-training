package com.user.app.dao;

import java.util.List;
import java.util.Optional;

import com.user.app.model.User;

public interface UserDao {
    User CreatesUser(User user) throws Exception;
    Optional<User> findById(Long userId) throws Exception;;
    Optional<User> findByEmail(String emailId) throws Exception; 
    List<User> findAll() throws Exception;
} 