package com.app.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.enums.Gender;
import com.app.enums.Status;
import com.app.model.User;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setUser_id(rs.getInt("user_id"));
        user.setName(rs.getString("name"));
        user.setPhn_number(rs.getString("phn_number"));
        user.setEmail(rs.getString("email"));
        user.setRole(rs.getString("role"));

        // Gender conversion using switch-case
        String genderCode = rs.getString("gender");
        switch (genderCode.toUpperCase()) {
            case "M":
                user.setGender(Gender.MALE);
                break;
            case "F":
                user.setGender(Gender.FEMALE);
                break;
            case "O":
                user.setGender(Gender.OTHER);
                break;
            default:
                throw new IllegalArgumentException("Unknown gender code: " + genderCode);
        }

        // Status conversion using switch-case
        String statusCode = rs.getString("status");
        switch (statusCode.toUpperCase()) {
            case "A":
                user.setStatus(Status.ACTIVE);
                break;
            case "I":
                user.setStatus(Status.INACTIVE);
                break;
            default:
                throw new IllegalArgumentException("Unknown status code: " + statusCode);
        }

        user.setDept(rs.getString("dept"));
        return user;
    }
}
