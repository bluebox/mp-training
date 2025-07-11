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
        User u = new User();
        u.setUser_id(rs.getInt("user_id"));
        u.setName(rs.getString("name"));
        u.setPhn_number(rs.getString("phn_number"));
        u.setEmail(rs.getString("email"));
        u.setRole(rs.getString("role"));

        String genderCode = rs.getString("gender");
        switch (genderCode) {
            case "M":
                u.setGender(Gender.MALE);
                break;
            case "F":
                u.setGender(Gender.FEMAL);
                break;
            case "O":
                u.setGender(Gender.OTHER);
                break;
            default:
                throw new SQLException("Unknown gender code: " + genderCode);
        }

        String statusCode = rs.getString("status");
        switch (statusCode) {
            case "A":
                u.setStatus(Status.ACTIVE);
                break;
            case "I":
                u.setStatus(Status.INACTIVE);
                break;
            default:
                throw new SQLException("Unknown status code: " + statusCode);
        }

        u.setDept(rs.getString("dept"));
        return u;
    }
}