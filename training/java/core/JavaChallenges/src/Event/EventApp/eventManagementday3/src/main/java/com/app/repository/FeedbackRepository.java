package com.app.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.Mapper.EventCreationMapper;
import com.app.Mapper.FeedbackMapper;
import com.app.model.EventCreation;
import com.app.model.Feedback;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class FeedbackRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addFeedback(Feedback feedback) throws Exception {
        String sql = "INSERT INTO feedback(user_id, event_id, rating, feedback) VALUES (?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, feedback.getUserId(), feedback.getEventId(), feedback.getRating(), feedback.getFeedback());
        if(rowsAffected > 0) {
        	log.info("Feedback added successfully!");
        }
        else {
        	throw new Exception("Feedback not added!");
        }
        return rowsAffected;
    }

    public int updateFeedback(Feedback feedback) throws Exception {
        String sql = "UPDATE feedback SET rating=?, feedback=? WHERE user_id=? AND event_id=?";
        int rowsAffected = jdbcTemplate.update(sql, feedback.getRating(), feedback.getFeedback(), feedback.getUserId(), feedback.getEventId());
        if(rowsAffected > 0) {
        	log.info("Feedback updated successfully!");
        }
        else {
        	throw new Exception("Feedback not updated!");
        }
        return rowsAffected;
    }

    public boolean deleteFeedback(int user_id, int event_id) {
        String sql = "DELETE FROM feedback WHERE user_id=? AND event_id=?";
        return jdbcTemplate.update(sql, user_id, event_id) == 1;
    }

    public List<Feedback> getAllFeedbackOfEvent(int eventId) throws Exception {
        String sql = "SELECT user_id,event_id,rating,feedback FROM feedback WHERE event_id=?";
        
        List<Feedback> feedbacks = null;

		try {
			feedbacks=jdbcTemplate.query(sql, new FeedbackMapper(), eventId);
		}
		catch(Exception e) {
		    throw new Exception("No Feedbacks found OR "+e.getMessage());
		}
		return feedbacks;
    }

    public List<Feedback> feedbackOfUser(int user_id) throws Exception {
        String sql = "SELECT user_id,event_id,rating,feedback FROM feedback WHERE user_id=?";
        List<Feedback> userFeedbacks = null;

		try {
			userFeedbacks=jdbcTemplate.query(sql, new FeedbackMapper(), user_id);
		}
		catch(Exception e) {
		    throw new Exception("No Feedbacks found OR "+e.getMessage());
		}
		return userFeedbacks;
    }
}
