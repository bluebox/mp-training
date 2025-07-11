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
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Response;
import com.app.model.User;
import com.app.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	private final UserService user;
	
	
	
	public UserController(UserService user)
	{
		this.user=user;
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<Response> addUser(@RequestBody User u) throws Exception
	{
		boolean isAdded=user.addUser(u);
		Response response=new Response();
		
		if(isAdded)
		{
			response.setStatusCode("200");
			response.setStatusMsg("User Added Successfully");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		else
		{
			response.setStatusCode("400");
			response.setStatusMsg("Error in Adding User");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Response> updateUser(@RequestBody User u) throws Exception
	{
		boolean isUpdated=user.updateUser(u);
		Response response=new Response();
		
		if(isUpdated)
		{
			response.setStatusCode("200");
			response.setStatusMsg("User Updated Successfully");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		else
		{
			response.setStatusCode("400");
			response.setStatusMsg("Error in Updating User");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteUser(@RequestBody int user_id) throws Exception
	{
		boolean isDeleted=user.deleteUser(user_id);
		Response response=new Response();
		
		if(isDeleted)
		{
			response.setStatusCode("200");
			response.setStatusMsg("User Deleted Successfully");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		else
		{
			response.setStatusCode("400");
			response.setStatusMsg("Error in Updating User");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() throws Exception
	{
		try {
			List<User> list=user.getAllUsers();
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
		
	}
	
	@GetMapping("/userById")
	public ResponseEntity<User> getUserById(int user_id) throws Exception
	{
		try {
			User u=user.getUserbyId(user_id);
			return ResponseEntity.status(HttpStatus.OK).body(u);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
		
	}

}
