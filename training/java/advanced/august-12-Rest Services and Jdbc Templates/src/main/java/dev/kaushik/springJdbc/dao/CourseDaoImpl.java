package dev.kaushik.springJdbc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import dev.kaushik.springJdbc.model.Course;

@Repository
public class CourseDaoImpl implements CourseDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void createCourse(Course course) throws DataAccessException {
        String sql = "INSERT INTO Course (name, hours, description) " +
                     "VALUES (:name, :hours, :description)";
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(course));
    }

    @Override
    public List<Course> listCourses(Course filter) throws DataAccessException {
        StringBuilder sql = new StringBuilder("SELECT id, name, hours, description FROM Course WHERE 1=1");
        MapSqlParameterSource params = new MapSqlParameterSource();

        if (filter != null) {
            if (filter.getName() != null && !filter.getName().isBlank()) {
                sql.append(" AND name LIKE :name");
                params.addValue("name", "%" + filter.getName() + "%");
            }

            if (filter.getHours() != null) {
                sql.append(" AND hours = :hours");
                params.addValue("hours", filter.getHours());
            }
        }

        return namedParameterJdbcTemplate.query(
                sql.toString(),
                params,
                new BeanPropertyRowMapper<>(Course.class)
        );
    }

}
