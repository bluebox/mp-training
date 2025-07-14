package com.app.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.Mapper.UserMapper;
import com.app.model.EventRegistration;
import com.app.model.User;

@Repository
public class EventRegistrationRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public boolean registerEvent(EventRegistration er) {
        String sql = "INSERT INTO eventRegistration(user_id, event_id, registration_status, registered_by, registered_at, updated_by, updated_at) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        return jdbc.update(sql,
                er.getUser_id(),
                er.getEvent_id(),
                er.getStatus() != null ? er.getStatus().getCode() : null,
                er.getRegistered_by(),
                er.getRegistered_at(),
                er.getUpdated_by(),
                er.getUpdated_at()) == 1;
    }

    public boolean updateRegistration(EventRegistration er) {
        String sql = "UPDATE eventRegistration SET registration_status = ?, updated_by = ?, updated_at = ? WHERE user_id = ? AND event_id = ?";

        return jdbc.update(sql,
                er.getStatus() != null ? er.getStatus().getCode() : null,
                er.getUpdated_by(),
                er.getUpdated_at(),
                er.getUser_id(),
                er.getEvent_id()) == 1;
    }

    public List<User> getAttendants(int event_id) {
        String sql = "SELECT u.* FROM users u JOIN eventRegistration e ON u.user_id = e.user_id WHERE e.event_id=? AND e.registration_status='A'";
        return jdbc.query(sql, new UserMapper(), event_id);
    }

    public List<User> getAbsenties(int event_id) {
        String sql = "SELECT u.* FROM users u JOIN eventRegistration e ON u.user_id = e.user_id WHERE e.event_id=? AND e.registration_status='N'";
        return jdbc.query(sql, new UserMapper(), event_id);
    }

    public List<User> getCancelledRegistrations(int event_id) {
        String sql = "SELECT u.* FROM users u JOIN eventRegistration e ON u.user_id = e.user_id WHERE e.event_id=? AND e.registration_status='C'";
        return jdbc.query(sql, new UserMapper(), event_id);
    }
}
