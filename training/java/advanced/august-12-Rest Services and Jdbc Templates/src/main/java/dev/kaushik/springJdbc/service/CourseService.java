package dev.kaushik.springJdbc.service;

import java.util.List;

import dev.kaushik.springJdbc.model.Course;
import jakarta.validation.Valid;

public interface CourseService {

    void createCourse(@Valid Course course);

    List<Course> listCourses(@Valid Course filter);
}
