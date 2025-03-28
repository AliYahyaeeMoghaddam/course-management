package com.example.course_managment.controller;

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
    public ResponseEntity<Course> createCourse(@RequestParam String name ,@RequestParam int unit ,
                                               @RequestParam Long college_id ,@RequestParam Long professor_id) {
        Course course = courseService.createCourse(name, unit, college_id, professor_id);
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course crs = courseService.getCourseById(id);
        return ResponseEntity.ok(crs);
    }

    @GetMapping("/{college_id}")
    public ResponseEntity<List<Course>> getCourseByCollegeId(@PathVariable Long college_id) {
        List<Course> courses = courseService.getCourseByCollegeId(college_id);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{professor_id}")
    public ResponseEntity<List<Course>> getCourseByProfessorId(@PathVariable Long professor_id) {
        List<Course> courses = courseService.getCourseByProfessorId(professor_id);
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id ,
                                               @RequestParam String name ,
                                               @RequestParam int unit ,
                                               @RequestParam Long college_id ,
                                               @RequestParam Long professor_id){
        Course crs = courseService.updateCourse(id, name, unit, college_id, professor_id);
        return ResponseEntity.ok(crs);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
    
}
