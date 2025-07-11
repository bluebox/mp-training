package com.app.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.EventRegistration;
import com.app.model.Response;
import com.app.model.User;
import com.app.service.EventRegistrationService;

@RestController
@RequestMapping("/api/eventRegistration")
@CrossOrigin(origins="*")
public class EventRegistrationController {
	
	@Autowired
	private final EventRegistrationService eventService;
	
	
	
	public EventRegistrationController(EventRegistrationService eventService)
	{
		this.eventService=eventService;
		
	}
	@PostMapping("/register")
	public ResponseEntity<Response>  register(@RequestBody EventRegistration er) throws Exception
	{
		Response response=new Response();
		
		boolean isAdded=eventService.registrationForEvent(er);
		if(isAdded)
		{
			response.setStatusCode("200");
			response.setStatusMsg("Registred for the Event Succesfully");
		}
		else
		{
			response.setStatusCode("400");
			response.setStatusMsg("Registration is Unsuccessfull");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Response>  updateRegistration(@RequestBody EventRegistration er) throws Exception
	{
		Response response=new Response();
		
		boolean isUpdated=eventService.updateEventRegistration(er);
		if(isUpdated)
		{
			response.setStatusCode("200");
			response.setStatusMsg("Updated Succesfully");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		else
		{
			response.setStatusCode("400");
			response.setStatusMsg("Updation is Unsuccessfull");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
	}
	
	@GetMapping("/attendants")
	public ResponseEntity<List<User>> eventAttendents(@RequestBody int event_id)
	{
		try {
			List<User> usersList =eventService.eventAttendents(event_id);
			return ResponseEntity.status(HttpStatus.OK).body(usersList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		
	}
	
	@GetMapping("/absenties")
	public ResponseEntity<List<User>> eventAbsenties(@RequestBody int event_id)
	{
		try {
			List<User> usersList =eventService.eventAbsenties(event_id);
			return ResponseEntity.status(HttpStatus.OK).body(usersList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@GetMapping("/cancelled")
	public ResponseEntity<List<User>> noOfUsersCancelledEventRegistration(@RequestBody int event_id)
	{
		try {
			List<User> usersList =eventService.noOfUsersCancelledEventRegistration(event_id);
			return ResponseEntity.status(HttpStatus.OK).body(usersList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

}
