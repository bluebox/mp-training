package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.EventRegistration;
import com.app.model.Response;
import com.app.model.User;
import com.app.service.EventRegistrationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/eventRegistration")
@Slf4j

public class EventRegistrationController {

	@Autowired
	private EventRegistrationService eventService;

	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody EventRegistration er) {
		Response response = new Response();
		try {
			if (eventService.registrationForEvent(er)) {
				response.setStatusCode("200");
				response.setStatusMsg("Registered for the event successfully");
				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			} else {
				response.setStatusCode("400");
				response.setStatusMsg("Registration unsuccessful");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}

		} catch (Exception e) {
			response.setStatusCode("500");
			response.setStatusMsg("Registration unsuccessful");
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

	}

	@PutMapping("/update")
	public ResponseEntity<Response> updateRegistration(@RequestBody EventRegistration er) {
		Response response = new Response();

		try {
			if (eventService.updateEventRegistration(er)) {
				response.setStatusCode("200");
				response.setStatusMsg("Updated successfully");
				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				
				response.setStatusCode("400");
				response.setStatusMsg("Update unsuccessful");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatusCode("500");
			response.setStatusMsg("Update unsuccessful");
			log.info(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PutMapping("/attendance")
	public ResponseEntity<Response> updateAttendance(@RequestBody EventRegistration er) {
		Response response = new Response();

		try {
			if (eventService.updateAttendanceOfUser(er)) {
				response.setStatusCode("200");
				response.setStatusMsg("Updated successfully");
				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				
				response.setStatusCode("400");
				response.setStatusMsg("Update unsuccessful");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatusCode("500");
			response.setStatusMsg("Update unsuccessful");
			log.info(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}


	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsersByRegistrationStatus(@RequestParam int eventId,
			@RequestParam String status) throws Exception {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(eventService.getUsersByRegistrationStatus(eventId, status));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}

}
