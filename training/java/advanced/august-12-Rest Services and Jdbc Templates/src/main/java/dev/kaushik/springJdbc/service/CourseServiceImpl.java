package dev.kaushik.springJdbc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import dev.kaushik.springJdbc.dao.CourseDao;
import dev.kaushik.springJdbc.model.Course;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated 
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    @Override
    public void createCourse(@Valid Course course) {
        courseDao.createCourse(course);
    }

    @Override
    public List<Course> listCourses(@Valid Course filter) {
        return courseDao.listCourses(filter);
    }
}
