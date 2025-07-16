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

        event.setEventId(rs.getInt("eventId"));
        event.setName(rs.getString("name"));
        event.setStartDate(rs.getTimestamp("startDate").toLocalDateTime());
        event.setEndDate(rs.getTimestamp("endDate").toLocalDateTime());
        event.setVenue(rs.getString("venue"));
        event.setEventOrganization(rs.getString("eventOrganization"));
        event.setEventCapacity(rs.getInt("eventCapacity"));
        event.setParticipantCount(rs.getInt("participantCount"));

        String statusCode = rs.getString("event_status");
        event.setStatus(EventStatus.fromCode(statusCode));

        event.setCreatedBy(rs.getInt("createdBy"));
        event.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());

        Timestamp updated = rs.getTimestamp("updatedAt");
        event.setUpdatedBy(rs.getInt("updatedBy"));
        event.setUpdatedAt(updated != null ? updated.toLocalDateTime() : null);

        return event;
    }
}