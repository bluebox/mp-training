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
        return jdbc.update(sql, feedback.getUser_id(), feedback.getEvent_id(), feedback.getRating(), feedback.getFeedback()) == 1;
    }

    public boolean updateFeedback(Feedback feedback) {
        String sql = "UPDATE feedback SET rating=?, feedback=? WHERE user_id=? AND event_id=?";
        return jdbc.update(sql, feedback.getRating(), feedback.getFeedback(), feedback.getUser_id(), feedback.getEvent_id()) == 1;
    }

    public boolean deleteFeedback(int user_id, int event_id) {
        String sql = "DELETE FROM feedback WHERE user_id=? AND event_id=?";
        return jdbc.update(sql, user_id, event_id) == 1;
    }

    public List<Feedback> getAllFeedbackOfEvent(int event_id) {
        String sql = "SELECT * FROM feedback WHERE event_id=?";
        return jdbc.query(sql, new FeedbackMapper(), event_id);
    }

    public List<Feedback> feedbackOfUser(int user_id) {
        String sql = "SELECT * FROM feedback WHERE user_id=?";
        return jdbc.query(sql, new FeedbackMapper(), user_id);
    }
}
