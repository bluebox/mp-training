package com.app.controller;

import com.app.model.Feedback;
import com.app.model.Response;
import com.app.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin("*")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/add")
    public ResponseEntity<Response> addFeedback(@RequestBody Feedback feedback) throws Exception {
        boolean isAdded = feedbackService.addFeedback(feedback);
        Response response = new Response();
        if (isAdded) {
            response.setStatusCode("200");
            response.setStatusMsg("Feedback added successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.setStatusCode("400");
            response.setStatusMsg("Error in adding feedback");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateFeedback(@RequestBody Feedback feedback) throws Exception {
        boolean isUpdated = feedbackService.updateFeedbak(feedback);
        Response response = new Response();
        if (isUpdated) {
            response.setStatusCode("200");
            response.setStatusMsg("Feedback updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.setStatusCode("400");
            response.setStatusMsg("Error in updating feedback");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
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
    public ResponseEntity<List<Feedback>> getAllFeedbackOfEvent(@RequestParam int event_id) {
        List<Feedback> list = feedbackService.getAllFeedbackofEvent(event_id);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/feedbackOfUser")
    public ResponseEntity<List<Feedback>> getFeedbackOfUser(@RequestParam int user_id) {
        List<Feedback> list = feedbackService.feedbackOfUser(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
