package com.kbc.KbcApp.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbc.KbcApp.dao.UserDao;
import com.kbc.KbcApp.handler.KbcException;
import com.kbc.KbcApp.pojos.Users;
import com.kbc.KbcApp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Transactional
	public void addUser(Users user) throws KbcException {
		if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
			throw new KbcException("Username cannot be null or empty");
		}
		if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
			throw new KbcException("Password cannot be null or empty");
		}
	
		
		List<Users> duplicateuser = userDao.getUser(null, user.getUsername(), null);
		if (duplicateuser.size() > 0) {
			throw new KbcException("Username already exists, please enter a new username");
		}
		
		user.setCreatedAt(LocalDateTime.now());
		
//		if (user.getRole() == null) {
//			user.setRole(RoleEnum.USER);
//		}
//		if (user.getStatus() == null) {
//			user.setStatus(StatusEnum.ACTIVE);
//		}
		
		userDao.addUser(user);
	}
	
	
	
	@Transactional
	public Optional<Users> validLogin(String username, String password) throws KbcException {
		if (username == null || password == null) {
			throw new KbcException("Username and password cannot be null");
		}
		
		List<Users> users = userDao.getUser(null, username, password);
		if (users.isEmpty()) {
			return Optional.empty();
		}
		Users user = users.get(0);

		if (user.getStatus().equals("INACTIVE") || !user.getPassword().equals(password)) {
	        return Optional.empty();
	    }

	    return Optional.of(user);
	}
	
	@Transactional
	public List<Users> getAllUsers() {
		return userDao.getUser(null, null, null);
	}
	
	@Transactional
	public Optional<Users> getUserById(int userId) throws KbcException {
		if (userId <= 0) {
			throw new KbcException("Invalid user ID");
		}
		
		List<Users> usersById = userDao.getUser(userId, null, null);
		if (usersById.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(usersById.get(0));
	}
	
	@Transactional
	public boolean updateUserRole(Users user, String modifiedBy) throws Exception {
		if (user.getUserId() == null) {
			throw new KbcException("User ID cannot be null");
		}
		if (user.getRole() == null) {
			throw new KbcException("Role cannot be null");
		}
		if (modifiedBy == null || modifiedBy.trim().isEmpty()) {
			throw new KbcException("ModifiedBy cannot be null or empty");
		}
		
		user.setModifiedAt(LocalDateTime.now());
		user.setModifiedBy(modifiedBy);
		userDao.updateUser(user);
		return true;
	}
	
	@Transactional
	public boolean updateUserStatus(Users user, String modifiedBy) throws Exception {
		if (user.getUserId() == null) {
			throw new KbcException("User ID cannot be null");
		}
		if (user.getStatus() == null) {
			throw new KbcException("Status cannot be null");
		}
		if (modifiedBy == null || modifiedBy.trim().isEmpty()) {
			throw new KbcException("ModifiedBy cannot be null or empty");
		}
		
		user.setModifiedAt(LocalDateTime.now());
		user.setModifiedBy(modifiedBy);
		userDao.updateUser(user);
		return true;
	}
	
	@Transactional
	public boolean updateUser(Users user, String modifiedBy) throws Exception {
		if (user.getUserId() == null) {
			throw new KbcException("User ID cannot be null");
		}
		if (modifiedBy == null || modifiedBy.trim().isEmpty()) {
			throw new KbcException("ModifiedBy cannot be null or empty");
		}
		List<Users> duplicateuser = userDao.getUser(null, user.getUsername(), null);
		if (duplicateuser.size() > 1) {
			throw new KbcException("Username already exists, please enter a new username");
		}
		
		user.setModifiedAt(LocalDateTime.now());
		user.setModifiedBy(modifiedBy);
		userDao.updateUser(user);
		return true;
	}
}
