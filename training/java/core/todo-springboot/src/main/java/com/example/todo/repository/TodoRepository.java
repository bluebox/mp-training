package com.example.todo.repository;

import com.example.todo.model.Todo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class TodoRepository {

    private final JdbcTemplate jdbcTemplate;

    public TodoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Todo> rowMapper = (rs, rowNum) -> new Todo(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getBoolean("completed")
    );

    public Todo save(Todo todo) {
        if (todo.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO todos (title, description, completed) VALUES (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, todo.getTitle());
                ps.setString(2, todo.getDescription());
                ps.setBoolean(3, todo.isCompleted());
                return ps;
            }, keyHolder);
            todo.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            jdbcTemplate.update(
                    "UPDATE todos SET title = ?, description = ?, completed = ? WHERE id = ?",
                    todo.getTitle(), todo.getDescription(), todo.isCompleted(), todo.getId()
            );
        }
        return todo;
    }

    public List<Todo> findAll() {
        return jdbcTemplate.query("SELECT * FROM todos", rowMapper);
    }

    public Todo findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM todos WHERE id = ?", rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM todos WHERE id = ?", id);
    }
}


