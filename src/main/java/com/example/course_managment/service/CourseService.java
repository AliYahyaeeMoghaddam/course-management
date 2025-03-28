package com.example.course_managment.service;

import com.example.course_managment.model.College;
import com.example.course_managment.model.Course;
import com.example.course_managment.model.Professor;
import com.example.course_managment.repository.CollegeRepository;
import com.example.course_managment.repository.CourseRepository;
import com.example.course_managment.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CollegeRepository collegeRepository;
    private final ProfessorRepository professorRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository ,
                         CollegeRepository collegeRepository ,
                         ProfessorRepository professorRepository) {
        this.courseRepository = courseRepository;
        this.collegeRepository = collegeRepository;
        this.professorRepository = professorRepository;
    }

    public Course createCourse(String name , int unit , Long college_id , Long professor_id) {
        College clg = collegeRepository.findById(college_id)
                .orElseThrow(() -> new RuntimeException("College not found"));
        Professor prof = professorRepository.findById(professor_id)
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        Course course = new Course();
        course.setCourse_name(name);
        course.setUnit(unit);
        course.setCollege(clg);
        course.setProfessor(prof);

        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public List<Course> getCourseByCollegeId(Long college_id) {
        return courseRepository.findByCollegeId(college_id);
    }

    public List<Course> getCourseByProfessorId(Long professor_id) {
        return courseRepository.findByProfessorId(professor_id);
    }

    public Course updateCourse(Long id ,String name , int unit ,
                               Long college_id ,
                               Long professor_id ) {
        Course crs = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        College clg = collegeRepository.findById(college_id)
                .orElseThrow(() -> new RuntimeException("College not found"));
        Professor prof = professorRepository.findById(professor_id)
                .orElseThrow(() -> new RuntimeException("Professor not found"));

        crs.setCourse_name(name);
        crs.setUnit(unit);
        crs.setCollege(clg);
        crs.setProfessor(prof);

        return courseRepository.save(crs);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

}