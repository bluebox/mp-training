package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Response;
import com.app.model.User;
import com.app.service.EmailService;
import com.app.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/add")
    public ResponseEntity<Response> addUser(@RequestBody User user) throws Exception {
        boolean added = userService.addUser(user);
        Response response = new Response();
        if (added) {
           
            emailService.sendRegistrationEmail(user.getEmail(), String.valueOf(user.getUser_id()));
            response.setStatusCode("200");
            response.setStatusMsg("User added successfully & email sent");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.setStatusCode("400");
            response.setStatusMsg("Failed to add user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateUser(@RequestBody User user) throws Exception {
        boolean updated = userService.updateUser(user);
        Response response = new Response();
        if (updated) {
            response.setStatusCode("200");
            response.setStatusMsg("User updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.setStatusCode("400");
            response.setStatusMsg("Failed to update user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteUser(@RequestParam int user_id) throws Exception {
        boolean deleted = userService.deleteUser(user_id);
        Response response = new Response();
        if (deleted) {
            response.setStatusCode("200");
            response.setStatusMsg("User deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.setStatusCode("400");
            response.setStatusMsg("Failed to delete user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() throws Exception {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/userById")
    public ResponseEntity<User> getUserById(@RequestParam int user_id) throws Exception {
        return ResponseEntity.ok(userService.getUserbyId(user_id));
    }
}
