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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.EventCreation;
import com.app.model.Response;
import com.app.model.User;
import com.app.service.EventCreationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/eventCreation")
@CrossOrigin(origins="*")
public class EventCreationController {

	private final EventCreationService eventCreationService;
	
	@Autowired
	public EventCreationController(EventCreationService eventCreationService) {
		this.eventCreationService=eventCreationService;
	}
	
//	create event
	@PostMapping(value="/createEvent")
	public ResponseEntity<Response> createEventPage(@RequestHeader("invocationFrom") String invocationFrom,@RequestBody EventCreation event) {
	    
			Response response = new Response();
	        log.info(String.format("Header invocationFrom = %s", invocationFrom));
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
	             response.setStatusMsg("Internal server error!"+e.getMessage());
	             return ResponseEntity
	                     .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                     .body(response);
			}
	}
	
//	update event
	@PutMapping(value="/updateEvent")
	public ResponseEntity<Response> updateEventPage(@RequestHeader("invocationFrom") String invocationFrom,@RequestBody EventCreation event) {
	    
			Response response = new Response();
	        log.info(String.format("Header invocationFrom = %s", invocationFrom));
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
	             response.setStatusMsg("Internal server error!"+e.getMessage());
	             return ResponseEntity
	                     .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                     .body(response);
			}
	}
	
//	cancle event
	@PostMapping(value="/cancleEvent")
	public ResponseEntity<Response> cancleEventPage(@RequestHeader("invocationFrom") String invocationFrom,@RequestParam int event_id) {
	    
			Response response = new Response();
	        log.info(String.format("Header invocationFrom = %s", invocationFrom));
	        try {
				boolean isEventCancalled = eventCreationService.cancleEvent(event_id);
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
	             response.setStatusMsg("Internal server error!");
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
	public EventCreation getEventByIdPage(@RequestParam int event_id) throws Exception {
	    
	        EventCreation event = eventCreationService.getEventById(event_id);
	        return event;		
	}
	
//	Delete event
	@DeleteMapping(value="/deleteEvent")
	public ResponseEntity<Response> deleteEventPage(@RequestHeader("invocationFrom") String invocationFrom,@RequestBody int event_id) {
	    
			Response response = new Response();
	        log.info(String.format("Header invocationFrom = %s", invocationFrom));
	        try {
				boolean isEventDeleted = eventCreationService.deleteEventById(event_id);
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
	             response.setStatusMsg("Internal server error!");
	             return ResponseEntity
	                     .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                     .body(response);
			}
	}
}
