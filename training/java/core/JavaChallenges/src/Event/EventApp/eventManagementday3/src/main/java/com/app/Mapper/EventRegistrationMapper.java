package com.app.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.app.enums.RegistrationStatus;
import com.app.model.EventRegistration;

public class EventRegistrationMapper implements RowMapper<EventRegistration> {
    @Override
    public EventRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
        EventRegistration er = new EventRegistration();

        er.setUserId(rs.getInt("userId"));
        er.setEventId(rs.getInt("eventId"));

        String statusCode = rs.getString("registration_status");
        er.setStatus(statusCode != null ? RegistrationStatus.fromCode(statusCode) : null);

        er.setRegisteredBy(rs.getInt("registeredBy"));
        er.setRegisteredAt(rs.getTimestamp("registeredAt").toLocalDateTime());

        int updatedBy = rs.getInt("updatedBy");
        er.setUpdatedBy(!rs.wasNull() ? updatedBy : null);

        Timestamp updated = rs.getTimestamp("updatedAt");
        er.setUpdatedAt(updated != null ? updated.toLocalDateTime() : null);

        return er;
    }
    
}

