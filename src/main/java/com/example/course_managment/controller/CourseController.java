package com.example.course_managment.controller;

import com.example.course_managment.dto.CourseDTO;
import com.example.course_managment.model.Course;
import com.example.course_managment.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody Course course) {
        CourseDTO crs = courseService.createCourse(course);
        return ResponseEntity.ok(crs);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CourseDTO> getCourseByName(@PathVariable String name) {
        CourseDTO crs = courseService.getCourseByName(name);
        return ResponseEntity.ok(crs);
    }

    @GetMapping("/{college_name}")
    public ResponseEntity<List<CourseDTO>> getCourseByCollegeId(@PathVariable String college_name) {
        List<CourseDTO> courses = courseService.getCourseByCollegeName(college_name);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{professor_id}")
    public ResponseEntity<List<CourseDTO>> getCourseByProfessorId(@PathVariable Long professor_id) {
        List<CourseDTO> courses = courseService.getCourseByProfessorId(professor_id);
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{name}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable String name ,
                                               @RequestBody Course course) {
        CourseDTO crs = courseService.updateCourse(name, course);
        return ResponseEntity.ok(crs);
    }

    @DeleteMapping("/{name}")
    public void deleteCourse(@PathVariable String name) {
        courseService.deleteCourse(name);
    }
    
}
