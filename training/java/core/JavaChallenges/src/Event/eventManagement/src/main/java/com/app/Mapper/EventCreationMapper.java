package com.app.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.app.enums.EventStatus;
import com.app.model.EventCreation;


public class EventCreationMapper implements RowMapper<EventCreation> {
	
	 public EventCreation mapRow(ResultSet rs, int rowNumber) throws SQLException {
	        EventCreation event = new EventCreation(); 
	        event.setEvent_id(rs.getInt("event_id"));
	        event.setName(rs.getString("name"));
	        LocalDateTime start_date = rs.getTimestamp("start_date").toLocalDateTime();
	        event.setStart_date(start_date);
	        LocalDateTime end_date = rs.getTimestamp("end_date").toLocalDateTime();
	        event.setEnd_date(end_date);
	        event.setVenue(rs.getString("venue"));
	        event.setEvent_organization(rs.getString("event_organization"));
	        event.setEvent_capacity(rs.getInt("event_capacity"));
	        event.setParticipant_count(rs.getInt("participant_count"));
//	        EventStatus event_status = EventStatus.valueOf(rs.getString("event_status"));
//	        event.setEvent_status(event_status);
	        String eventStatChar = rs.getString("event_status");
	        if ("A".equalsIgnoreCase(eventStatChar)) {
	        	event.setStatus(EventStatus.ACTIVE);
			} 
	        else if ("F".equalsIgnoreCase(eventStatChar)) {
	        	event.setStatus(EventStatus.FINISHED);
			}
	        else if ("C".equalsIgnoreCase(eventStatChar)) {
	        	event.setStatus(EventStatus.CANCELLED);
			}
	        event.setCreated_by(rs.getInt("created_by"));
	        Timestamp created_at = rs.getTimestamp("created_at");
	        event.setCreated_at(created_at.toLocalDateTime());
	        event.setUpdated_by(rs.getInt("updated_by"));
//	        LocalDateTime updated_at = rs.getTimestamp("updated_at").toLocalDateTime();
	        Timestamp updated_at = rs.getTimestamp("updated_at");
	        if(updated_at != null) {
	        event.setUpdated_at(updated_at.toLocalDateTime());	
	        }
	        else {
	        	event.setUpdated_at(null);
	        }
	        return event;
	    }
}
