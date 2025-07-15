package com.app.controller;

import com.app.model.EventRegistration;
import com.app.model.Response;
import com.app.model.User;
import com.app.service.EventRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventRegistration")
@CrossOrigin("*")
public class EventRegistrationController {

	@Autowired
	private EventRegistrationService eventService;

	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody EventRegistration er) throws Exception {
		Response response = new Response();
		boolean isAdded = eventService.registrationForEvent(er);
		if (isAdded) {
			response.setStatusCode("200");
			response.setStatusMsg("Registered for the event successfully");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			response.setStatusCode("400");
			response.setStatusMsg("Registration unsuccessful");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Response> updateRegistration(@RequestBody EventRegistration er) throws Exception {
		Response response = new Response();
		boolean isUpdated = eventService.updateEventRegistration(er);
		if (isUpdated) {
			response.setStatusCode("200");
			response.setStatusMsg("Updated successfully");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response.setStatusCode("400");
			response.setStatusMsg("Update unsuccessful");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@PutMapping("/attendance")
	public ResponseEntity<Response> updateAttendance(@RequestParam String status, @RequestParam int event_id,
			@RequestParam int user_id, @RequestParam int updated_by) throws Exception {
		Response response = new Response();
		boolean isUpdated = eventService.updateAttendanceOfUser(status, event_id, user_id, updated_by);
		if (isUpdated) {
			response.setStatusCode("200");
			response.setStatusMsg("Updated successfully");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response.setStatusCode("400");
			response.setStatusMsg("Updated unsuccessful");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@GetMapping("/attendants")
	public ResponseEntity<List<User>> getAttendants(@RequestParam int event_id) {
		List<User> usersList = eventService.eventAttendents(event_id);
		return ResponseEntity.status(HttpStatus.OK).body(usersList);
	}

	@GetMapping("/absenties")
	public ResponseEntity<List<User>> getAbsenties(@RequestParam int event_id) {
		List<User> usersList = eventService.eventAbsenties(event_id);
		return ResponseEntity.status(HttpStatus.OK).body(usersList);
	}

	@GetMapping("/cancelled")
	public ResponseEntity<List<User>> getCancelledRegistrations(@RequestParam int event_id) {
		List<User> usersList = eventService.noOfUsersCancelledEventRegistration(event_id);
		return ResponseEntity.status(HttpStatus.OK).body(usersList);
	}
}
