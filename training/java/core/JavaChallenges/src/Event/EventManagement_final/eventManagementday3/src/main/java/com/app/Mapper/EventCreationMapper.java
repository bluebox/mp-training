package com.app.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.app.enums.EventStatus;
import com.app.model.EventCreation;

public class EventCreationMapper implements RowMapper<EventCreation> {
    @Override
    public EventCreation mapRow(ResultSet rs, int rowNum) throws SQLException {
        EventCreation event = new EventCreation();

        event.setEventId(rs.getInt("event_id"));
        event.setName(rs.getString("name"));
        event.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
        event.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());
        event.setVenue(rs.getString("venue"));
        event.setEventOrganizer(rs.getString("event_organization"));
        event.setEventCapacity(rs.getInt("event_capacity"));
        event.setParticipantCount(rs.getInt("participant_count"));

        String statusCode = rs.getString("event_status");
        event.setStatus(EventStatus.fromCode(statusCode));
        event.setCreatedBy(rs.getInt("created_by"));
        event.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

        Timestamp updated = rs.getTimestamp("updated_at");
        event.setUpdatedBy(rs.getInt("updated_by"));
        event.setUpdatedAt(updated != null ? updated.toLocalDateTime() : null);

        return event;
    }
}