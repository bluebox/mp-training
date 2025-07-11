package com.app.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.Mapper.UserMapper;
import com.app.model.EventRegistration;
import com.app.model.User;

@Repository
public class EventRegistrationRepository {
	
	@Autowired
	private final JdbcTemplate jdbcTemplate;
	
	public EventRegistrationRepository(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate=jdbcTemplate;
	}
	
	public int registrationForEvent(EventRegistration er) throws Exception
	{
		String sql="INSERT INTO eventRegistration "
				+ "(user_id,event_id,registration_status,registered_by,registered_at) "
				+ "VALUES(?,?,?,?,?)";
		int rows=jdbcTemplate.update(
				sql,
				er.getUser_id(),
				er.getEvent_id(),
				er.getStatus(),
				er.getRegistered_by(),
				er.getRegistered_at()
				);
		if(rows>0)
		{
			System.out.println("Registration Successful");
		}
		else
		{
			throw new Exception("Registration unSuccessful");
		}
		return rows;
				
	}
	
	public int updateEventRegistration(EventRegistration er) throws Exception
	{
		String sql="UPDATE eventRegistration SET "
				+ "registration_status=? , updated_by=? ,updated_at=? where event_id=? and user_id=?";
		int rows=jdbcTemplate.update(
				sql,
				er.getStatus().getStatus(),
				er.getUpdated_by(),
				er.getUpdated_at(),
				er.getEvent_id(),
				er.getUser_id()
				);
		if(rows>0)
		{
			System.out.println("Updation Successful");
		}
		else
		{
			throw new Exception("Updation unSuccessful");
		}
		return rows;
	}
	
	public List<User> eventAttendents(int event_id) throws Exception
	{
		if(event_id<0)
		{
			throw new Exception("Invalid Event_Id");
			
		}
		String sql="select * from users where user_id in "
				+ "(select user_id from eventRegistration where registration_status=? ,event_id=?)";
		return jdbcTemplate.query(sql, new UserMapper(),'A',event_id);
	}
	
	public List<User> eventAbsenties(int event_id) throws Exception
	{
		if(event_id<0)
		{
			throw new Exception("Invalid Event_Id");
			
		}
		String sql="select * from users where user_id in "
				+ "(select user_id from eventRegistration where registration_status=? ,event_id=?)";
		return jdbcTemplate.query(sql, new UserMapper(),'N',event_id);
	}
	
	public List<User> noOfUsersCancelledEventRegistration(int event_id) throws Exception
	{
		if(event_id<0)
		{
			throw new Exception("Invalid Event_Id");
			
		}
		String sql="select * from users where user_id in "
				+ "(select user_id from eventRegistration where registration_status=? ,event_id=?)";
		return jdbcTemplate.query(sql, new UserMapper(),'C',event_id);
	}
	

}
