package com.app.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.enums.RegistrationStatus;
import com.app.model.EventRegistration;

public class EventRegistrationMapper implements RowMapper<EventRegistration> 
{
	
	@Override
	public EventRegistration mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		EventRegistration er=new EventRegistration();
		er.setUser_id(rs.getInt("user_id"));
		er.setEvent_id(rs.getInt("event_id"));
		String ch=rs.getString("status");
		RegistrationStatus r=RegistrationStatus.valueOf(ch);
		er.setStatus(r);
		er.setRegistered_by(rs.getInt("registered_by"));
		er.setRegistered_at(rs.getTimestamp("registered_at").toLocalDateTime());
		er.setUpdated_by(rs.getInt("updated_by"));
		er.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
		
		
		return er;
	}

}
