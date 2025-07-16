package com.app.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.model.Feedback;

public class FeedbackMapper implements RowMapper<Feedback> {

    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        Feedback fb = new Feedback();
        fb.setUserId(rs.getInt("userId"));
        fb.setEventId(rs.getInt("eventId"));
        fb.setRating(rs.getInt("rating"));
        fb.setFeedback(rs.getString("feedback"));
        return fb;
    }
}
