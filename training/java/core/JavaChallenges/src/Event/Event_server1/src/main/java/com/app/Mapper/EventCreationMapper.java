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

        event.setEvent_id(rs.getInt("event_id"));
        event.setName(rs.getString("name"));
        event.setStart_date(rs.getTimestamp("start_date").toLocalDateTime());
        event.setEnd_date(rs.getTimestamp("end_date").toLocalDateTime());
        event.setVenue(rs.getString("venue"));
        event.setEvent_organization(rs.getString("event_organization"));
        event.setEvent_capacity(rs.getInt("event_capacity"));
        event.setParticipant_count(rs.getInt("participant_count"));

        String statusCode = rs.getString("event_status");
        event.setStatus(EventStatus.fromCode(statusCode));

        event.setCreated_by(rs.getInt("created_by"));
        event.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());

        Timestamp updated = rs.getTimestamp("updated_at");
        event.setUpdated_by(rs.getInt("updated_by"));
        event.setUpdated_at(updated != null ? updated.toLocalDateTime() : null);

        return event;
    }
}