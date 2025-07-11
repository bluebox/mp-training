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
	private final JdbcTemplate jdbcTemplate;
	
	public FeedbackRepository(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate=jdbcTemplate;
	}
	public int  addFeedback(Feedback feedback) throws Exception
	{
		String sql="INSERT INTO feedback (user_id,event_id,rating,feedback) VALUES (?, ?, ?, ?)";
		
		int rows=jdbcTemplate.update(
				sql,
				feedback.getUser_id(),
				feedback.getEvent_id(),
				feedback.getRating(),
				feedback.getFeedback()
				);
		if(rows>0)
		{
			System.out.println("Feedback Submitted Successfully");
		}
		else
		{
			throw new Exception("Error in submitting Feedback");
		}
		return rows;
	}
	public int updateFeedbak(Feedback feedback) throws Exception
	{
		String sql="UPDATE feedback SET rating=?,feedback=? where user_id=? and event_id=?";
		int rows=jdbcTemplate.update(
				sql,
				feedback.getRating(),
				feedback.getFeedback(),
				feedback.getUser_id(),
				feedback.getEvent_id()			
				);
		if(rows>0)
		{
			System.out.println("Feedback Updated Successfully");
		}
		else
		{
			throw new Exception("Error in Updating Feedback");
		}
		return rows;
	}
	
	public int deleteFeedback(int user_id,int event_id) throws Exception
	{
		String sql="DELETE FROM feedback where user_id=? and event_id=?";
		int rows=jdbcTemplate.update(
				sql,
				user_id,
				event_id
				);
		if(rows>0)
		{
			System.out.println("Feedback Deleted Successfully");
		}
		else
		{
			throw new Exception("Error in Deleting Feedback");
		}
		return rows;
	}
	
	public List<Feedback> getAllFeedbackofEvent(int event_id) throws Exception
	{
		if(event_id<0)
		{
			throw new Exception("Invalid event_id");
		}
		String sql="select * from feedback where event_id=?";
		return jdbcTemplate.query(sql,new FeedbackMapper());
	}
	
	public List<Feedback> feedbackNotgivenByUsers(int event_id)
	{
		return null;
	}
	
	public List<Feedback> feedbackOfUser(int user_id) throws Exception
	{
		if(user_id<0)
		{
			throw new Exception("Invalid user_id");
		}
		String sql="select * from feedback where user_id=?";
		return jdbcTemplate.query(sql,new FeedbackMapper(),user_id);
	}

}
