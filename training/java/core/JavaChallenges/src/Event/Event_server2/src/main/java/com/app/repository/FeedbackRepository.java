package com.app.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.Mapper.FeedbackMapper;
import com.app.model.Feedback;

@Repository
public class FeedbackRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public boolean addFeedback(Feedback feedback) {
        String sql = "INSERT INTO feedback(user_id, event_id, rating, feedback) VALUES (?, ?, ?, ?)";
        return jdbc.update(sql, feedback.getUserId(), feedback.getEventId(), feedback.getRating(), feedback.getFeedback()) == 1;
    }

    public boolean updateFeedback(Feedback feedback) {
        String sql = "UPDATE feedback SET rating=?, feedback=? WHERE user_id=? AND event_id=?";
        return jdbc.update(sql, feedback.getRating(), feedback.getFeedback(), feedback.getUserId(), feedback.getEventId()) == 1;
    }

    public boolean deleteFeedback(int userId, int eventId) {
        String sql = "DELETE FROM feedback WHERE user_id=? AND event_id=?";
        return jdbc.update(sql, userId, eventId) == 1;
    }

    public List<Feedback> getAllFeedbackOfEvent(int eventId) {
        String sql = "SELECT * FROM feedback WHERE event_id=?";
        return jdbc.query(sql, new FeedbackMapper(), eventId);
    }

    public List<Feedback> feedbackOfUser(int userId) {
        String sql = "SELECT * FROM feedback WHERE user_id=?";
        return jdbc.query(sql, new FeedbackMapper(), userId);
    }
}
