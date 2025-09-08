package com.medplus.Roles_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.medplus.Roles_backend.domain.User;
import com.medplus.Roles_backend.dto.ApiResponse;
import com.medplus.Roles_backend.dto.PasswordChangeRequest;
import com.medplus.Roles_backend.service.UserServiceInterface;

import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(@RequestBody User loginRequest) {
        return userService.login(loginRequest);
    }


    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse<Void>> changePassword(@RequestBody PasswordChangeRequest request) {

        return userService.changePassword(request.getUserId(), request.getNewPassword());
    }

}
