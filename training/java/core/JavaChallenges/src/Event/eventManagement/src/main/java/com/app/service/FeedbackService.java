package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Feedback;
import com.app.repository.FeedbackRepository;

@Service
public class FeedbackService {
	
	@Autowired
	private final FeedbackRepository fb;
	
	public FeedbackService(FeedbackRepository fb)
	{
		this.fb=fb;
	}
	
	public boolean  addFeedback(Feedback feedback) throws Exception
	{
		boolean isAdded=false;
		int rows=fb.addFeedback(feedback);
		if(rows>0)
		{
			isAdded=true;
		}
		return isAdded;
	}
	
	public boolean updateFeedbak(Feedback feedback) throws Exception
	{
		boolean isUpdated=false;
		int rows=fb.updateFeedbak(feedback);
		if(rows>0)
		{
			isUpdated=true;
		}
		return isUpdated;
	}
	
	public boolean deleteFeedback(int user_id,int event_id) throws Exception
	{
		boolean isDeleted=false;
		int rows=fb.deleteFeedback(user_id, event_id);
		if(rows>0)
		{
			isDeleted=true;
		}
		return isDeleted;
	}
	
	public List<Feedback> getAllFeedbackofEvent(int event_id) throws Exception
	{
		return fb.getAllFeedbackofEvent(event_id);
	}
	
	public List<Feedback> feedbackOfUser(int user_id) throws Exception
	{
		return fb.feedbackOfUser(user_id);
	}

}
