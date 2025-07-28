package com.app.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.Mapper.UserMapper;
import com.app.model.EventRegistration;
import com.app.model.User;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class EventRegistrationRepository {

	@Autowired
	private JdbcTemplate jdbc;

	public int registerEvent(EventRegistration er) throws Exception {
		String sql = "INSERT INTO eventRegistration(user_id, event_id, registration_status, registered_by, registered_at, updated_by, updated_at) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		int rowsAffected = jdbc.update(sql, er.getUserId(), er.getEventId(),
				er.getStatus() != null ? er.getStatus().getCode() : null, er.getRegisteredBy(), er.getRegisteredAt(),
				er.getUpdatedBy(), er.getUpdatedAt());
		if (rowsAffected > 0) {
			log.info("Registration for Event Successful");
		} else {
			throw new Exception("Error in Registration");
		}

		return rowsAffected;
	}

	public int updateRegistration(EventRegistration er) throws Exception {
		String sql = "UPDATE eventRegistration SET registration_status = ?, updated_by = ?, updated_at = ? WHERE user_id = ? AND event_id = ?";
		int rowsAffected = jdbc.update(sql, er.getStatus() != null ? er.getStatus().getCode() : null, er.getUpdatedBy(),
				er.getUpdatedAt(), er.getUserId(), er.getEventId());
		if (rowsAffected > 0) {
			log.info("Updation Successfull");
		} else {
			throw new Exception("Error in Updation");
		}

		return rowsAffected;
	}


	public List<User> getUsersByRegistrationStatus(int eventId, String status) throws Exception {
		List<User> users = null;

		try {
			String sql = "SELECT u.user_id,u.name,u.phn_number,u.email,u.role,u.gender,u.status,u.dept FROM users u JOIN eventRegistration e ON u.user_id = e.user_id WHERE e.event_id=? AND e.registration_status=?";
			users = jdbc.query(sql, new UserMapper(), eventId, status);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw new Exception("No Users");

		}
		return users;
	}
}