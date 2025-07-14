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

        er.setUser_id(rs.getInt("user_id"));
        er.setEvent_id(rs.getInt("event_id"));

        String statusCode = rs.getString("registration_status");
        er.setStatus(statusCode != null ? RegistrationStatus.fromCode(statusCode) : null);

        er.setRegistered_by(rs.getInt("registered_by"));
        er.setRegistered_at(rs.getTimestamp("registered_at").toLocalDateTime());

        int updatedBy = rs.getInt("updated_by");
        er.setUpdated_by(!rs.wasNull() ? updatedBy : null);

        Timestamp updated = rs.getTimestamp("updated_at");
        er.setUpdated_at(updated != null ? updated.toLocalDateTime() : null);

        return er;
    }
    
}
