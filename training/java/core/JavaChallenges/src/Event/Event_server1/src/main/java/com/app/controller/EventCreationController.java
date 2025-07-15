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

    @GetMapping("/all")
    public ResponseEntity<List<EventCreation>> getAllEvents() {
        List<EventCreation> list = eventCreationService.getAllEvents();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @GetMapping("/get")
    public ResponseEntity<EventCreation> getEventById(@RequestParam int event_id) {
        EventCreation event = eventCreationService.getEventById(event_id);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }
    @GetMapping("/all/{user_id}")
    public List<EventCreation> getAllWithRegistrationStatus(@PathVariable int user_id) {
       return eventCreationService.getEventsForUser(user_id);
    }

}
