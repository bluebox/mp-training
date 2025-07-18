package com.user.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.app.dao.UserDao;
import com.user.app.model.User;
import com.user.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User createUser(User user) throws Exception {
    	validateUser(user);
    	Optional<User> existingUser = userDao.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("User with email already exists");
        }
        
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userDao.CreatesUser(user);
    }

    @Override
    public Optional<User> getUserById(Long userId) throws Exception {
        return userDao.findById(userId);
    }
    
    @Override
    public Optional<User> login(String emailId, String rawPassword) throws Exception{
    	validateLoginInput(emailId,rawPassword);
    	Optional<User> userOpt = userDao.findByEmail(emailId);
    	
    	if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (passwordEncoder.matches(rawPassword, user.getPassword())){
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() throws Exception{
        return userDao.findAll();
    }

	@Override
	public User updateUser(Long userId, User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void validateUser(User user) {
        if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (user.getEmail() == null || !user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Valid email is required");
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
    }

    private void validateLoginInput(String email, String password) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
    }
    
} 