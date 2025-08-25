package com.EventManagement.EMS_Backend.DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.EventManagement.EMS_Backend.Model.EventApproveStatus;
import com.EventManagement.EMS_Backend.Model.EventCategory;
import com.EventManagement.EMS_Backend.Model.EventStatus;
import com.EventManagement.EMS_Backend.Model.ModelEvent;

import java.util.List;

@Repository
public class StudentDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EventRowMapper eventrowmapper;

    
    public int registerForEvent(int userId, int eventId) {
        String sql = "INSERT INTO event_registration (user_id, event_id, status) VALUES (?, ?, 'Registered')";
        return jdbcTemplate.update(sql, userId, eventId);
    }

    
//    public List<ModelEventRegistrati> getUserRegistrations(int userId) {
//        String sql = "SELECT * FROM event_registration WHERE user_id = ?";
//        return jdbcTemplate.query(sql, new EventRegistrationMapper(), userId);
//    }

 
    public String getRegistrationStatus(int userId, int eventId) {
        String sql = "SELECT status FROM event_registration WHERE user_id = ? AND event_id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, userId, eventId);
    }

    
    public int cancelRegistration(int userId, int eventId) {
        String sql = "UPDATE event_registration SET status = 'Cancelled' WHERE user_id = ? AND event_id = ?";
        return jdbcTemplate.update(sql, userId, eventId);
    }
    
    public List<ModelEvent> viewallevents(String userid){
		String sql="select event.Eventid,Eventname,Eventdatetime,Eventvenue,Eventcategory,Eventstatus,Approved_by,Approved_time,created_by,created_time,EventDescription,event.Status,event.Modified_by,event.Modified_at from registration Inner Join event on registration.eventid=event.eventid where userid=? and registration.status='registered'";
		System.out.println("userid"+userid);
	    List<ModelEvent> events = jdbcTemplate.query(sql,new Object[]{userid},eventrowmapper);
	    return events;

		
	}
    public String getuserid(String email) {
    	
    	String sql="select userid from user where email=?";
    	return jdbcTemplate.queryForObject(sql, String.class, email);

    }
    public int feedback(int eventid,String userid,String description,int rating)
    {
        String sql = "INSERT INTO feedback (userid, eventid,description,rating) VALUES (?, ?,?,?)";
        return jdbcTemplate.update(sql,userid,eventid,description,rating);
    }
}





