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
                event.getEvent_id(), event.getName(), event.getStart_date(), event.getEnd_date(),
                event.getVenue(), event.getEvent_organization(), event.getEvent_capacity(),
                event.getParticipant_count(),
                event.getStatus() != null ? event.getStatus().getCode() : null,
                event.getCreated_by(), event.getCreated_at(),
                event.getUpdated_by(), event.getUpdated_at()) == 1;
    }

    public boolean updateEvent(EventCreation event) {
        String sql = "UPDATE eventCreation SET name=?, start_date=?, end_date=?, venue=?, event_organization=?, event_capacity=?, participant_count=?, event_status=?, updated_by=?, updated_at=? WHERE event_id=?";
        return jdbc.update(sql,
                event.getName(), event.getStart_date(), event.getEnd_date(), event.getVenue(),
                event.getEvent_organization(), event.getEvent_capacity(), event.getParticipant_count(),
                event.getStatus() != null ? event.getStatus().getCode() : null,
                event.getUpdated_by(), event.getUpdated_at(), event.getEvent_id()) == 1;
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

    public EventCreation getEventById(int event_id) {
        String sql = "SELECT * FROM eventCreation WHERE event_id=?";
        return jdbc.queryForObject(sql, new EventCreationMapper(), event_id);
    }
    
    public List<EventCreation> findAllWithUserStatus(int userId) {
        String sql = "SELECT ec.event_id, ec.name, ec.venue, ec.start_date, ec.end_date, er.registration_status FROM eventCreation ec LEFT JOIN eventRegistration er ON ec.event_id = er.event_id AND er.user_id = ? WHERE ec.event_status = 'A'";

        return jdbc.query(sql, new EventCreationMapper(),userId );
    }

    
}