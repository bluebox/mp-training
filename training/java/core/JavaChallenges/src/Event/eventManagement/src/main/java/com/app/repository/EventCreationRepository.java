package com.app.repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.model.EventCreation;
import com.app.Mapper.EventCreationMapper;

@Repository
public class EventCreationRepository {
	
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public EventCreationRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

//	Create Event
	public int createEvent(EventCreation event) throws Exception{
		String eventSql = "INSERT INTO eventCreation(event_id,name,start_date,end_date,venue,event_organization,"
				+ "event_capacity,event_status,created_by,created_at) "
				+ "values (?,?,?,?,?,?,?,?,?,?)";
		
		int rowsAffected = jdbcTemplate.update(eventSql,
				event.getEvent_id(),
				event.getName(),
				event.getStart_date(),
				event.getEnd_date(),
				event.getVenue(),
				event.getEvent_organization(),
				event.getEvent_capacity(),
				event.getStatus().getStatus(),
				event.getCreated_by(),
				event.getCreated_at()
				);
		if(rowsAffected > 0) {
			System.out.println("Event created Successfully!");
		}
		else {
			throw new Exception("Event is not created!");
		}
		
		return rowsAffected;
	}
	

	
//	Update Event
	public int updateEvent(EventCreation event) throws Exception{
		
		int rowsAffected = 0;
        long noOfDays = LocalDateTime.now().until(event.getStart_date(), ChronoUnit.DAYS);
        if(noOfDays > 1) {
		String eventUpdateSql = "UPDATE eventCreation SET name=?,start_date=?,end_date=?,venue=?,event_organization=?,"
				+ "event_capacity=?,updated_by=?,updated_at=? WHERE event_id=?";
		
		rowsAffected = jdbcTemplate.update(eventUpdateSql,
				event.getName(),
				event.getStart_date(),
				event.getEnd_date(),
				event.getVenue(),
				event.getEvent_organization(),
				event.getEvent_capacity(),
				event.getUpdated_by(),
				event.getUpdated_at(),
				event.getEvent_id()
				);
        }
		
		if(rowsAffected > 0) {
			System.out.println("Event is updated Successfully!");
        }
        else {
        	throw new Exception("This event is not allowed to edit ,due to less time available to start event. i.e : "+ noOfDays );
        }
		
		return rowsAffected;
	}
	
//	Cancle Event
	public int cancleEvent(int event_id) throws Exception{
		
		int rowsAffected = 0;
		EventCreation rs = jdbcTemplate.queryForObject("SELECT * FROM eventCreation WHERE event_id=?",new EventCreationMapper(),event_id);
		LocalDateTime event_start_date = rs.getStart_date();
        long noOfDays = LocalDateTime.now().until(event_start_date, ChronoUnit.DAYS);
        if(noOfDays > 1) {
		String eventCancleSql = "UPDATE eventCreation SET event_status = ?,updated_by = ?,updated_at = ? WHERE event_id = ?)";
		rowsAffected = jdbcTemplate.update(eventCancleSql,
				"C",
				rs.getUpdated_by(),
				rs.getUpdated_at(),
				event_id
				);
        }
        if(rowsAffected > 0) {
    		System.out.println("Event is canclled Successfully!");
        }
        else {
        	throw new Exception("This event is not allowed to cancle ,due to less time available to start event. i.e : " + noOfDays);
        }
        return rowsAffected;
	}

//	Get All Events
	public  List<EventCreation> getAllEvents() throws Exception{
		String eventsSql="SELECT * FROM eventCreation";
		List<EventCreation> events = null;
//		return 	jdbcTemplate.query(eventsSql, new EventCreationMapper());

		try {
			events=jdbcTemplate.query(eventsSql, new EventCreationMapper());
		}
		catch(Exception e) {
		    throw new Exception("No Events found "+e.getMessage());
		}
		return events;
	}
	
//	Get Event By Event ID
	public EventCreation getEventById(int event_id) throws Exception{
		String eventSql="SELECT * FROM eventCreation WHERE event_id=?";
		EventCreation event = null;
		try {
			event=jdbcTemplate.queryForObject(eventSql, new EventCreationMapper(), event_id);
		}
		catch(Exception e) {
		    throw new Exception("No Event found on this Event Id : " + event_id +e.getMessage());
		}
		return event;
	}

//	Delete Event By Event Id
	public int deleteEventById(int event_id) throws Exception{
		
		int rowsAffected = 0;
		EventCreation rs = jdbcTemplate.queryForObject("SELECT * FROM eventCreation WHERE event_id=?",new EventCreationMapper(),event_id);
		LocalDateTime event_start_date = rs.getStart_date();
        long noOfDays = LocalDateTime.now().until(event_start_date, ChronoUnit.DAYS);
        if(noOfDays > 1) {
			String eventSql="DELETE FROM eventCreation WHERE event_id=?";
			rowsAffected = jdbcTemplate.update(eventSql, event_id);
        }
		if(rowsAffected > 0) {
			System.out.println("Event Deleted Successfully!");
		}
		else {
        	throw new Exception("This event is not allowed to delete ,due to less time available to start event. i.e : " + noOfDays);
		}
		return rowsAffected;
	}
}
