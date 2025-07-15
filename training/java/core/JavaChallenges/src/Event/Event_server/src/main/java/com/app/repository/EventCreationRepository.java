package com.app.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.Mapper.EventCreationMapper;
import com.app.model.EventCreation;

@Repository
public class EventCreationRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public boolean createEvent(EventCreation event) {
        String sql = "INSERT INTO eventCreation(event_id, name, start_date, end_date, venue, event_organization, event_capacity, participant_count, event_status, created_by, created_at, updated_by, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbc.update(sql,
                event.getEventId(), event.getName(), event.getStartDate(), event.getEndDate(),
                event.getVenue(), event.getEventOrganization(), event.getEventCapacity(),
                event.getParticipantCount(),
                event.getStatus() != null ? event.getStatus().getCode() : null,
                event.getCreatedBy(), event.getCreatedAt(),
                event.getUpdatedBy(), event.getUpdatedAt()) == 1;
    }

    public boolean updateEvent(EventCreation event) {
        String sql = "UPDATE eventCreation SET name=?, start_date=?, end_date=?, venue=?, event_organization=?, event_capacity=?, participant_count=?, event_status=?, updated_by=?, updated_at=? WHERE event_id=?";
        return jdbc.update(sql,
                event.getName(), event.getStartDate(), event.getEndDate(), event.getVenue(),
                event.getEventOrganization(), event.getEventCapacity(), event.getParticipantCount(),
                event.getStatus() != null ? event.getStatus().getCode() : null,
                event.getUpdatedBy(), event.getUpdatedAt(), event.getEventId()) == 1;
    }
    
    public void updateEventStatus(int event_id)
    {
    	 String sql = "UPDATE eventCreation SET event_status='F' WHERE event_id=?";
         jdbc.update(sql, event_id);
    }

    public boolean cancelEvent(int event_id) {
        String sql = "UPDATE eventCreation SET event_status='C' WHERE event_id=?";
        return jdbc.update(sql, event_id) == 1;
    }

    public boolean deleteEventById(int event_id) {
        String sql = "DELETE FROM eventCreation WHERE event_id=?";
        return jdbc.update(sql, event_id) == 1;
    }

    public List<EventCreation> getAllEvents() {
        String sql = "SELECT * FROM eventCreation";
        return jdbc.query(sql, new EventCreationMapper());
    }

    
    public List<EventCreation> getActiveEvents() {     
    	 String sql = "SELECT * FROM eventCreation WHERE event_status='A'";
         return jdbc.query(sql, new EventCreationMapper());
    }
    
    public List<EventCreation> getCancelledEvents() { 
    	String sql = "SELECT * FROM eventCreation WHERE event_status='C'";
    return jdbc.query(sql, new EventCreationMapper());
    }
    
    public List<EventCreation>  getFinishedEvents() {
    	String sql = "SELECT * FROM eventCreation WHERE event_status='F'";
        return jdbc.query(sql, new EventCreationMapper());
    }
    
    public EventCreation getEventById(int event_id) {
        String sql = "SELECT * FROM eventCreation WHERE event_id=?";
        return jdbc.queryForObject(sql, new EventCreationMapper(), event_id);
    }
    
    public boolean increaseParticipantsCount(int event_id,int total,int available)
    {
    	if(available+1>total)
    	{
    		return false;
    	}
    	String sql = "UPDATE eventCreation SET participation_count=? WHERE event_id=?";
        return jdbc.update(sql,available+1, event_id) == 1;
    }
    
    public boolean decreaseParticipantsCount(int event_id,int available)
    {
    	String sql = "UPDATE eventCreation SET participation_count=? WHERE event_id=?";
        return jdbc.update(sql,available-1, event_id) == 1;
    }
    
    public List<EventCreation> findAllEventsAttendedByUser(int userId) {
        String sql = "SELECT ec.event_id, ec.name, ec.venue, ec.start_date, ec.end_date, er.registration_status FROM eventCreation ec LEFT JOIN eventRegistration er ON ec.event_id = er.event_id AND er.user_id = ? WHERE er.registration_status = 'A'";

        return jdbc.query(sql, new EventCreationMapper(),userId );
    }
    
    public List<EventCreation> findAllEventsCancelledByUser(int userId) {
        String sql = "SELECT ec.event_id, ec.name, ec.venue, ec.start_date, ec.end_date, er.registration_status FROM eventCreation ec LEFT JOIN eventRegistration er ON ec.event_id = er.event_id AND er.user_id = ? WHERE er.registration_status = 'C'";

        return jdbc.query(sql, new EventCreationMapper(),userId );
    }
    
    public List<EventCreation> findAllEventsRegisteredByUser(int userId) {
        String sql = "SELECT ec.event_id, ec.name, ec.venue, ec.start_date, ec.end_date, er.registration_status FROM eventCreation ec LEFT JOIN eventRegistration er ON ec.event_id = er.event_id AND er.user_id = ? WHERE er.registration_status = 'R'";

        return jdbc.query(sql, new EventCreationMapper(),userId );
    }
    
    public List<EventCreation> findAllEventsNotAttendedUser(int userId) {
        String sql = "SELECT ec.event_id, ec.name, ec.venue, ec.start_date, ec.end_date, er.registration_status FROM eventCreation ec LEFT JOIN eventRegistration er ON ec.event_id = er.event_id AND er.user_id = ? WHERE er.registration_status = 'N'";

        return jdbc.query(sql, new EventCreationMapper(),userId );
    }
    
    
}