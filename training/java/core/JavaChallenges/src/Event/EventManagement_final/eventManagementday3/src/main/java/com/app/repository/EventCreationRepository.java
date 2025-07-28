package com.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.Mapper.EventCreationMapper;
import com.app.model.EventCreation;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class EventCreationRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public EventCreationRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int createEvent(EventCreation event) throws Exception {
		String eventSql = "INSERT INTO eventCreation(event_id,name,start_date,end_date,venue,event_organization,"
				+ "event_capacity,participant_count,event_status,created_by,created_at) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?)";

		int rowsAffected = jdbcTemplate.update(eventSql, event.getEventId(), event.getName(), event.getStartDate(),
				event.getEndDate(), event.getVenue(), event.getEventOrganizer(), event.getEventCapacity(),
				event.getParticipantCount(), event.getStatus() != null ? event.getStatus().getCode() : null,
				event.getCreatedBy(), event.getCreatedAt());

		if (rowsAffected > 0) {
			log.info("Event created Successfully!");
		} else {
			throw new Exception("Event is not created!");
		}
		return rowsAffected;
	}

	public int updateEvent(EventCreation event) throws Exception {

		int rowsAffected = 0;
		String sql = "UPDATE eventCreation SET name=?, start_date=?, end_date=?, venue=?, event_organization=?, event_capacity=?, participant_count=?, event_status=?, updated_by=?, updated_at=? WHERE event_id=?";
		rowsAffected = jdbcTemplate.update(sql, event.getName(), event.getStartDate(), event.getEndDate(),
				event.getVenue(), event.getEventOrganizer(), event.getEventCapacity(), event.getParticipantCount(),
				event.getStatus() != null ? event.getStatus().getCode() : null, event.getUpdatedBy(),
				event.getUpdatedAt(), event.getEventId());

		if (rowsAffected > 0) {
			log.info("Event is updated Successfully!");
		} else {
			throw new Exception("Event is not updated!");
		}
		return rowsAffected;
	}

	public int cancleEvent(int eventId) throws Exception {

		int rowsAffected = 0;
		String eventCancleSql = "UPDATE eventCreation SET event_status = ? WHERE event_id = ?";
		rowsAffected = jdbcTemplate.update(eventCancleSql, "C", eventId);

		if (rowsAffected > 0) {
			log.info("Event is canclled Successfully!");
		} else {
			throw new Exception("This event is not canclelled !");
		}
		return rowsAffected;
	}

	public List<EventCreation> getAllEvents() throws Exception {
		String eventsSql = "SELECT event_id,name,start_date,end_date,venue,event_organization,event_capacity,participant_count,event_status,created_by,created_at,updated_by,updated_at FROM eventCreation";
		List<EventCreation> events = null;

		try {
			events = jdbcTemplate.query(eventsSql, new EventCreationMapper());
		} catch (Exception e) {
			throw new Exception("No Events found OR " + e.getMessage());
		}
		return events;
	}

	public EventCreation getEventById(int eventId) throws Exception {
		String eventSql = "SELECT event_id,name,start_date,end_date,venue,event_organization,event_capacity,participant_count,event_status,created_by,created_at,updated_by,updated_at FROM eventCreation WHERE event_id=?";
		EventCreation event = null;
		try {
			event = jdbcTemplate.queryForObject(eventSql, new EventCreationMapper(), eventId);
		} catch (Exception e) {
			throw new Exception("No Event found on this Event Id : " + eventId + " OR " + e.getMessage());
		}
		return event;
	}

	public int deleteEventById(int eventId) throws Exception {

		String eventSql = "DELETE FROM eventCreation WHERE event_id=?";
		int rowsAffected = jdbcTemplate.update(eventSql, eventId);
		if (rowsAffected > 0) {
			System.out.println("Event Deleted Successfully!");
		} else {
			throw new Exception("This event is not deleted!");
		}
		return rowsAffected;
	}

	public boolean updateParticipantsCount(int count, int eventId) {

		String sql = "UPDATE eventCreation SET participant_count=? WHERE event_id=?";
		log.info(count+"");
		return jdbcTemplate.update(sql, count, eventId) == 1;
	}


	public List<EventCreation> getEventsByUserStatus(int userId, String status) throws Exception {
		List<EventCreation> events = null;

		try {
			String sql = "SELECT ec.event_id, ec.name, ec.venue, ec.start_date, ec.end_date,ec.event_organization,ec.event_capacity,ec.participant_count,ec.event_status,ec.created_by,ec.created_at,ec.updated_by,ec.updated_at, er.registration_status FROM eventCreation ec LEFT JOIN eventRegistration er ON ec.event_id = er.event_id WHERE er.user_id = ? AND er.registration_status = ?";
			events = jdbcTemplate.query(sql, new EventCreationMapper(), userId, status);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw new Exception("No Events found!");

		}
		return events;
	}

	public List<EventCreation> getActiveEvents() {
		String sql = "SELECT event_id,name,start_date,end_date,venue,event_organization,event_capacity,participant_count,event_status,created_by,created_at,updated_by,updated_at FROM eventCreation WHERE event_status='A'";
		return jdbcTemplate.query(sql, new EventCreationMapper());
	}
	
	public int updateEventStatus(int eventId, String status) {
	    String sql = "UPDATE eventCreation SET event_status = ?, updated_at = ? WHERE event_id = ?";
	    return jdbcTemplate.update(sql, status, LocalDateTime.now(), eventId);
	}


}