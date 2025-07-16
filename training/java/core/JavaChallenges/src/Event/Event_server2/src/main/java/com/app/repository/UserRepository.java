package com.app.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.app.Mapper.UserMapper;
import com.app.model.User;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public boolean addUser(User u) {
        String sql = "INSERT INTO users(user_id, name, phn_number, email, role, gender, status, dept) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbc.update(sql,
                u.getUserId(), u.getName(), u.getPhnNumber(), u.getEmail(), u.getRole(),
                u.getGender().getCode(), u.getStatus().getCode(), u.getDept()) == 1;
    }

    public boolean updateUser(User u) {
        String sql = "UPDATE users SET name=?, phn_number=?, email=?, role=?, gender=?, status=?, dept=? WHERE user_id=?";
        return jdbc.update(sql,
                u.getName(), u.getPhnNumber(), u.getEmail(), u.getRole(),
                u.getGender().getCode(), u.getStatus().getCode(), u.getDept(), u.getUserId()) == 1;
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id=?";
        return jdbc.update(sql, userId) == 1;
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbc.query(sql, new UserMapper());
    }

    public User getUserbyId(int userId) {
        String sql = "SELECT * FROM users WHERE user_id=?";
        return jdbc.queryForObject(sql, new UserMapper(), userId);
    }
}
