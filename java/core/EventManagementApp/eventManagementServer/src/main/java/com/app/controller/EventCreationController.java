package com.app.controller;

import com.app.model.EventCreation;
import com.app.model.Response;
import com.app.model.User;
import com.app.service.EventCreationService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/eventCreation")
@Slf4j
@CrossOrigin("*")
public class EventCreationController {

	private final EventCreationService eventCreationService;
	
	@Autowired
	public EventCreationController(EventCreationService eventCreationService) {
		this.eventCreationService=eventCreationService;
	}
	
//	create event
	@PostMapping(value="/createEvent")
	public ResponseEntity<Response> createEventPage(@Valid @RequestBody EventCreation event) {
	    
			Response response = new Response();
	        try {
				boolean isEventCreated = eventCreationService.createEvent(event);
				System.out.println(isEventCreated);
				if(isEventCreated) {
					response.setStatusCode("200");
					response.setStatusMsg("Event created successfully!");
					return ResponseEntity
							.status(HttpStatus.CREATED)
							.header("isEventCreated", "true")
							.body(response);
				}
				else {
					 	response.setStatusCode("400");
			            response.setStatusMsg("Event not created!");
			            return ResponseEntity
			                    .status(HttpStatus.BAD_REQUEST)
			                    .body(response);
				}
			} 
	        catch (Exception e) {
	        	 response.setStatusCode("500");
	             response.setStatusMsg("Internal server error! due to, "+e.getMessage());
	             log.error(e.getMessage());
	             return ResponseEntity
	                     .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                     .body(response);
			}
	}
	
//	update event
	@PutMapping(value="/updateEvent")
	public ResponseEntity<Response> updateEventPage(@Valid @RequestBody EventCreation event) {
	    
			Response response = new Response();
	        try {
				boolean isEventUpdated= eventCreationService.updateEvent(event);
				if(isEventUpdated) {
					response.setStatusCode("200");
					response.setStatusMsg("Event updated successfully!");
					return ResponseEntity
							.status(HttpStatus.CREATED)
							.header("isEventUpdated", "true")
							.body(response);
				}
				else {
					 response.setStatusCode("400");
			            response.setStatusMsg("Event not updated!");
			            return ResponseEntity
			                    .status(HttpStatus.BAD_REQUEST)
			                    .body(response);
				}
			} 
	        catch (Exception e) {
	        	 response.setStatusCode("500");
	             response.setStatusMsg("Internal server error! due to, "+e.getMessage());
	             log.error(e.getMessage());
	             return ResponseEntity
	                     .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                     .body(response);
			}
	}
	
//	cancle event
	@PostMapping(value="/cancleEvent")
	public ResponseEntity<Response> cancleEventPage(@Valid @RequestParam int eventId) {
	    
			Response response = new Response();
	        try {
				boolean isEventCancalled = eventCreationService.cancleEvent(eventId);
				if(isEventCancalled) {
					response.setStatusCode("200");
					response.setStatusMsg("Event cancalled successfully!");
					return ResponseEntity
							.status(HttpStatus.CREATED)
							.header("isEventCancalled", "true")
							.body(response);
				}
				else {
					 response.setStatusCode("400");
			            response.setStatusMsg("Event not canclled!");
			            return ResponseEntity
			                    .status(HttpStatus.BAD_REQUEST)
			                    .body(response);
				}
			} 
	        catch (Exception e) {
	        	 response.setStatusCode("500");
	             response.setStatusMsg("Internal server error! "+e.getMessage());
	             log.error(e.getMessage());
	             return ResponseEntity
	                     .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                     .body(response);
			}
	}
	
//	Get All Events
	@GetMapping(value="/events")
	public List<EventCreation>  getAllEventsPage() throws Exception{
		    List<EventCreation> events = eventCreationService.getAllEvents();
			return events;
	}
	

	
//	Get Event By ID
	@GetMapping(value="/event")
	public EventCreation getEventByIdPage(@Valid @RequestParam int eventId) throws Exception {
	    
	        EventCreation event = eventCreationService.getEventById(eventId);
	        return event;		
	}
	
//	Delete event
	@DeleteMapping(value="/deleteEvent")
	public ResponseEntity<Response> deleteEventPage(@Valid @RequestParam int eventId) {
	    
			Response response = new Response();
	        try {
				boolean isEventDeleted = eventCreationService.deleteEventById(eventId);
				if(isEventDeleted) {
					response.setStatusCode("200");
					response.setStatusMsg("Event deleted successfully!");
					return ResponseEntity
							.status(HttpStatus.CREATED)
							.header("isEventDeleted", "true")
							.body(response);
				}
				else {
					 response.setStatusCode("400");
			            response.setStatusMsg("Event not deleted!");
			            return ResponseEntity
			                    .status(HttpStatus.BAD_REQUEST)
			                    .body(response);
				}
			} 
	        catch (Exception e) {
	        	 response.setStatusCode("500");
	             response.setStatusMsg("Internal server error!"+e.getMessage());
	             log.error(e.getMessage());
	             return ResponseEntity
	                     .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                     .body(response);
			}
	}
	
	@GetMapping("/eventsStatus")
	public ResponseEntity<List<EventCreation>> getEventByStatus(@RequestParam int userId,@RequestParam String status) throws Exception {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(eventCreationService.getEventsByUserStatus(userId,status));
		} catch (Exception e) {
            log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}
	

	@GetMapping("/activeEvents")
	public ResponseEntity<List<EventCreation>> getActiveEvents() {
		List<EventCreation> list = eventCreationService.getActiveEvents();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

}
