package dev.kaushik.springJdbc.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import dev.kaushik.springJdbc.model.Course;

public interface CourseDao {
	public void createCourse(Course course) throws DataAccessException;

	public List<Course> listCourses(Course course) throws DataAccessException;
}
