package com.medplus.Roles_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.medplus.Roles_backend.dao.RoleDaoInterface;
import com.medplus.Roles_backend.dao.UserDaoInterface;
import com.medplus.Roles_backend.domain.User;
import com.medplus.Roles_backend.dto.ApiResponse;

import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private RoleDaoInterface roleDao;

    @Autowired
    private UserDaoInterface userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<ApiResponse<Map<String, Object>>> login(User loginRequest) {
        User dbUser = userDao.findById(loginRequest.getId());

        if (dbUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "User not found!", null));
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), dbUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, "Invalid password!", null));
        }
        dbUser.setRoles(roleDao.getAssignedActiveRoles(dbUser.getId()));

        Map<String, Object> userData = new HashMap<>();
        userData.put("id", dbUser.getId());
        userData.put("firstName", dbUser.getFirstname());
        userData.put("lastName", dbUser.getLastname());
        userData.put("username", dbUser.getUsername());
        userData.put("roles", dbUser.getRoles());

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Login successful", userData)
        );
    }

    public ResponseEntity<ApiResponse<Void>> changePassword(String userId, String newPassword) {
        User dbUser = userDao.findById(userId);

        if (dbUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "User not found!", null));
        }
        String encodedPassword = passwordEncoder.encode(newPassword);
        userDao.updatePassword(userId, encodedPassword);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Password changed successfully", null)
        );
    }
}
