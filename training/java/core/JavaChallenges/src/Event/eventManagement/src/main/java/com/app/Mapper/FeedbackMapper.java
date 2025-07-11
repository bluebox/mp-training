package com.app.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.model.Feedback;

public class FeedbackMapper implements RowMapper<Feedback>{

	@Override
	public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
		Feedback fb=new Feedback();
		fb.setUser_id(rs.getInt("user_id"));
		fb.setEvent_id(rs.getInt("event_id"));
		fb.setRating(rs.getInt("rating"));
		fb.setFeedback(rs.getString("feedback"));
		return fb;
	}

}
