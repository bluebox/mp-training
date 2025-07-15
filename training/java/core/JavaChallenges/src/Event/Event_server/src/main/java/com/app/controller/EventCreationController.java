package com.app.controller;

import com.app.model.EventCreation;
import com.app.model.Response;
import com.app.service.EventCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventCreation")
@CrossOrigin("*")
public class EventCreationController {

	@Autowired
	private EventCreationService eventCreationService;

	@PostMapping("/create")
	public ResponseEntity<Response> createEvent(@RequestBody EventCreation event) throws Exception {
		boolean isCreated = eventCreationService.createEvent(event);
		Response response = new Response();
		if (isCreated) {
			response.setStatusCode("200");
			response.setStatusMsg("Event created successfully");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			response.setStatusCode("400");
			response.setStatusMsg("Event creation failed");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Response> updateEvent(@RequestBody EventCreation event) throws Exception {
		boolean isUpdated = eventCreationService.updateEvent(event);
		Response response = new Response();
		if (isUpdated) {
			response.setStatusCode("200");
			response.setStatusMsg("Event updated successfully");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response.setStatusCode("400");
			response.setStatusMsg("Event update failed");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@PostMapping("/cancel")
	public ResponseEntity<Response> cancelEvent(@RequestParam int event_id) throws Exception {
		boolean isCancelled = eventCreationService.cancelEvent(event_id);
		Response response = new Response();
		if (isCancelled) {
			response.setStatusCode("200");
			response.setStatusMsg("Event cancelled successfully");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response.setStatusCode("400");
			response.setStatusMsg("Event cancellation failed");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteEvent(@RequestParam int event_id) throws Exception {
		boolean isDeleted = eventCreationService.deleteEventById(event_id);
		Response response = new Response();
		if (isDeleted) {
			response.setStatusCode("200");
			response.setStatusMsg("Event deleted successfully");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response.setStatusCode("400");
			response.setStatusMsg("Event deletion failed");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@GetMapping("/get")
	public ResponseEntity<EventCreation> getEventById(@RequestParam int event_id) {
		EventCreation event = eventCreationService.getEventById(event_id);
		return ResponseEntity.status(HttpStatus.OK).body(event);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<EventCreation>> getAllEvents() {
		List<EventCreation> list = eventCreationService.getAllEvents();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}



	@GetMapping("/activeEvents")
	public ResponseEntity<List<EventCreation>> getActiveEvents() {
		List<EventCreation> list = eventCreationService.getActiveEvents();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@GetMapping("/cancelledEvents")
	public ResponseEntity<List<EventCreation>> getCancelledEvents() {
		List<EventCreation> list = eventCreationService.getCancelledEvents();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	
	@GetMapping("/finishedEvents")
	public ResponseEntity<List<EventCreation>> getFinishedEvents() {
		List<EventCreation> list = eventCreationService.getFinishedEvents();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}


	@GetMapping("/attendad")
	public ResponseEntity<List<EventCreation>> findAllEventsAttendedByUser(@RequestParam int user_id) {
		List<EventCreation> event = eventCreationService.findAllEventsAttendedByUser(user_id);
		return ResponseEntity.status(HttpStatus.OK).body(event);
	}

	@GetMapping("/cancel")
	public ResponseEntity<List<EventCreation>> findAllEventsCancelledByUser(@RequestParam int user_id) {
		List<EventCreation> event = eventCreationService.findAllEventsCancelledByUser(user_id);
		return ResponseEntity.status(HttpStatus.OK).body(event);
	}

	@GetMapping("/registered")
	public ResponseEntity<List<EventCreation>> findAllEventsRegisteredByUser(@RequestParam int user_id) {
		List<EventCreation> event = eventCreationService.findAllEventsRegisteredByUser(user_id);
		return ResponseEntity.status(HttpStatus.OK).body(event);
	}

	@GetMapping("/absented")
	public ResponseEntity<List<EventCreation>> findAllEventsNotAttendedUser(@RequestParam int user_id) {
		List<EventCreation> event = eventCreationService.findAllEventsNotAttendedUser(user_id);
		return ResponseEntity.status(HttpStatus.OK).body(event);
	}
//    @GetMapping("/all/{user_id}")
//    public List<EventCreation> getAllWithRegistrationStatus(@PathVariable int user_id) {
//       return eventCreationService.getEventsForUser(user_id);
//    }

}
