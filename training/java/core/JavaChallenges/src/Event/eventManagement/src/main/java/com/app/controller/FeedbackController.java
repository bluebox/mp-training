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

import com.app.model.Feedback;
import com.app.model.Response;
import com.app.service.FeedbackService;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins="*")
public class FeedbackController 
{
	@Autowired
	private final FeedbackService fb;
	

	
	public FeedbackController(FeedbackService fb)
	{
		this.fb=fb;
	
	}
	
	@PostMapping("/add")
	public ResponseEntity<Response>  addFeedback(@RequestBody Feedback feedback) throws Exception
	{
		boolean isAdded=fb.addFeedback(feedback);
		Response response=new Response();
		
		if(isAdded)
		{
			response.setStatusCode("200");
			response.setStatusMsg("Feedback Added Successfully");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		else
		{
			response.setStatusCode("400");
			response.setStatusMsg("Error in Adding Feedback");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
			
	}
	@PutMapping("/update")
	public ResponseEntity<Response> updateFeedbak(@RequestBody Feedback feedback) throws Exception
	{
		boolean isUpdated=fb.updateFeedbak(feedback);
		Response response=new Response();
		
		if(isUpdated)
		{
			response.setStatusCode("200");
			response.setStatusMsg("Updated SuccessFully");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		else
		{
			response.setStatusCode("400");
			response.setStatusMsg("Error in Updating Feedback");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteFeedback(@RequestBody int user_id,@RequestBody int event_id) throws Exception
	{
		boolean isDeleted=fb.deleteFeedback(user_id, event_id);
		Response response=new Response();
		if(isDeleted)
		{
			response.setStatusCode("200");
			response.setStatusMsg("Deleted SuccessFully");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		else
		{
			response.setStatusCode("400");
			response.setStatusMsg("Error in Deleting Feedback");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	@GetMapping("/feedbacks")
	public ResponseEntity<List<Feedback>> getAllFeedbackofEvent(@RequestBody int event_id)
	{
		try {
			List<Feedback> list=fb.getAllFeedbackofEvent(event_id);
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
	}
	@GetMapping("/feedbackOfUser")
	public ResponseEntity<List<Feedback>> feedbackOfUser(@RequestBody int user_id)
	{
		try {
			List<Feedback> list=fb.feedbackOfUser(user_id);
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
	}

}
