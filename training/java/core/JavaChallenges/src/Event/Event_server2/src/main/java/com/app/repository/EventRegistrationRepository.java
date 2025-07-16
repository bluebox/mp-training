package com.app.repository;

import java.time.LocalDateTime;
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
                er.getUserId(),
                er.getEventId(),
                er.getStatus() != null ? er.getStatus().getCode() : null,
                er.getRegisteredBy(),
                er.getRegisteredAt(),
                er.getUpdatedBy(),
                er.getUpdatedAt()) == 1;
    }

    public boolean updateRegistration(EventRegistration er) {
        String sql = "UPDATE eventRegistration SET registration_status = ?, updated_by = ?, updated_at = ? WHERE user_id = ? AND event_id = ?";

        return jdbc.update(sql,
                er.getStatus() != null ? er.getStatus().getCode() : null,
                er.getUpdatedBy(),
                er.getUpdatedAt(),
                er.getUserId(),
                er.getEventId()) == 1;
    }
    
    public boolean updateAttendanceOfUser(String status,int event_id,int user_id,int updated_by)
    {
        String sql = "UPDATE eventRegistration SET registration_status = ?, updated_by = ?, updated_at = ? WHERE user_id = ? AND event_id = ?";
        return jdbc.update(sql,status,updated_by,LocalDateTime.now(),user_id,event_id)==1;

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
    
    public List<User> getEventRegistredUsers(int event_id) {
        String sql = "SELECT u.* FROM users u JOIN eventRegistration e ON u.user_id = e.user_id WHERE e.event_id=? AND e.registration_status='R'";
        return jdbc.query(sql, new UserMapper(), event_id);
    }
}
