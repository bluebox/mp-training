package com.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.EventCreation;
import com.app.model.Feedback;
import com.app.repository.EventCreationRepository;
import com.app.repository.FeedbackRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepo;

    @Autowired
    private EventCreationRepository eventRepo;

    public boolean addFeedback(Feedback feedback) throws Exception {
    	
        EventCreation event = eventRepo.getEventById(feedback.getEventId());
        validateFeedbackWindow(event.getEndDate());
       
        return feedbackRepo.addFeedback(feedback)>0;
    }

    public boolean updateFeedbak(Feedback feedback) throws Exception {
    	boolean isUpdated = false;
        EventCreation event = eventRepo.getEventById(feedback.getEventId());
        validateFeedbackWindow(event.getEndDate());
        int res = feedbackRepo.updateFeedback(feedback);
        return isUpdated = res > 0 ? true:false;
    }

    public boolean deleteFeedback(int user_id, int event_id) {
        return feedbackRepo.deleteFeedback(user_id, event_id);
    }

    public List<Feedback> getAllFeedbackofEvent(int event_id) throws Exception {
        return feedbackRepo.getAllFeedbackOfEvent(event_id);
    }

    public List<Feedback> feedbackOfUser(int user_id) throws Exception {
        return feedbackRepo.feedbackOfUser(user_id);
    }

    private void validateFeedbackWindow(LocalDateTime endDateTime) throws Exception {
        LocalDate endDate = endDateTime.toLocalDate();
        LocalDate today = LocalDate.now();
        if (today.isBefore(endDate)) {
            throw new Exception("Feedback can only be given after the event ends");
        }
        if (today.isAfter(endDate.plusDays(2))) {
            throw new Exception("Feedback window has closed (only allowed within 2 days of event end)");
        }
        
    }
}

