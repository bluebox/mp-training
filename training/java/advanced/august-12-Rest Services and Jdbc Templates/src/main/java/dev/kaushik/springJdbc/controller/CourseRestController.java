package dev.kaushik.springJdbc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.kaushik.springJdbc.model.Course;
import dev.kaushik.springJdbc.service.CourseService;

@RestController
@RequestMapping("/api")
public class CourseRestController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/listCourses")
    public List<Course> getAllCourses() {
        return courseService.listCourses(null);
    }
    
    @PostMapping(value = "/addCourse", consumes = "application/json", produces = "application/json")
    public Map<String, String> addCourse(@RequestBody Course course) {
        courseService.createCourse(course);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Course added successfully");
        return response;
    }
    
}
