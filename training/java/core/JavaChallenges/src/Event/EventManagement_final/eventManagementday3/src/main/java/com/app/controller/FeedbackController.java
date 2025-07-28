package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Feedback;
import com.app.model.Response;
import com.app.service.FeedbackService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/feedback")
@Slf4j
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/add")
    public ResponseEntity<Response> addFeedback(@RequestBody Feedback feedback) throws Exception {
        
        Response response = new Response();
        try {
            boolean isFeedbackAdded = feedbackService.addFeedback(feedback);
			if(isFeedbackAdded) {
				response.setStatusCode("200");
				response.setStatusMsg("Feedback done successfully!");
				return ResponseEntity
						.status(HttpStatus.CREATED)
						.header("isFeedbackAdded", "true")
						.body(response);
			}
			else {
				 	response.setStatusCode("400");
		            response.setStatusMsg("Feedback not done!");
		            return ResponseEntity
		                    .status(HttpStatus.BAD_REQUEST)
		                    .body(response);
			}
		} 
        catch (Exception e) {
        	log.error(e.getMessage());
        	 response.setStatusCode("500");
             response.setStatusMsg("Internal server error! due to, "+e.getMessage());
             return ResponseEntity
                     .status(HttpStatus.INTERNAL_SERVER_ERROR)
                     .body(response);
		}
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateFeedback(@RequestBody Feedback feedback) throws Exception {
        
    	Response response = new Response();
        try {
            boolean isFeedbackUpdated = feedbackService.updateFeedbak(feedback);
			if(isFeedbackUpdated) {
				response.setStatusCode("200");
				response.setStatusMsg("Feedback updated successfully!");
				return ResponseEntity
						.status(HttpStatus.CREATED)
						.header("isFeedbackUpdated", "true")
						.body(response);
			}
			else {
				 response.setStatusCode("400");
		            response.setStatusMsg("Feedback not updated!");
		            return ResponseEntity
		                    .status(HttpStatus.BAD_REQUEST)
		                    .body(response);
			}
		} 
        catch (Exception e) {
        	 log.error(e.getMessage());
        	 response.setStatusCode("500");
             response.setStatusMsg("Internal server error! due to, "+e.getMessage());
             return ResponseEntity
                     .status(HttpStatus.INTERNAL_SERVER_ERROR)
                     .body(response);
		}
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteFeedback(@RequestParam int user_id, @RequestParam int event_id) throws Exception {
        boolean isDeleted = feedbackService.deleteFeedback(user_id, event_id);
        Response response = new Response();
        if (isDeleted) {
            response.setStatusCode("200");
            response.setStatusMsg("Feedback deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.setStatusCode("400");
            response.setStatusMsg("Error in deleting feedback");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/feedbacks")
    public List<Feedback> getAllFeedbackOfEvent(@RequestParam int eventId) throws Exception {
        List<Feedback> feedbacks = feedbackService.getAllFeedbackofEvent(eventId);
        return feedbacks;
    }

    @GetMapping("/feedbackOfUser")
    public List<Feedback> getFeedbackOfUser(@RequestParam int user_id) throws Exception {
        List<Feedback> userFeedbacks = feedbackService.feedbackOfUser(user_id);
        return userFeedbacks;
    }
}
