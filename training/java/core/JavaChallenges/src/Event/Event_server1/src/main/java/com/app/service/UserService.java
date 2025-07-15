package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public boolean addUser(User u) {
        return repo.addUser(u);
    }

    public boolean updateUser(User u) {
        return repo.updateUser(u);
    }

    public boolean deleteUser(int user_id) {
        return repo.deleteUser(user_id);
    }

    public List<User> getAllUsers() {
        return repo.getAllUsers();
    }

    public User getUserbyId(int user_id) {
        return repo.getUserbyId(user_id);
    }
}