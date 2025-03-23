package com.example.course_managment.service;

import com.example.course_managment.model.Course;
import com.example.course_managment.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Course updateCourse(Long id , Course course) {
        Course crs = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        crs.setCourse_name(course.getCourse_name());
        crs.setUnit(course.getUnit());

        return courseRepository.save(crs);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

}
