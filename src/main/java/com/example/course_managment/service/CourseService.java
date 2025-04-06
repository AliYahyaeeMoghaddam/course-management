package com.example.course_managment.service;

import com.example.course_managment.dto.CourseDTO;
import com.example.course_managment.exception.CollegeNotFoundException;
import com.example.course_managment.exception.CourseNotFoundException;
import com.example.course_managment.exception.ProfessorNotFoundException;
import com.example.course_managment.mapper.CourseMapper;
import com.example.course_managment.model.College;
import com.example.course_managment.model.Course;
import com.example.course_managment.model.Professor;
import com.example.course_managment.repository.CollegeRepository;
import com.example.course_managment.repository.CourseRepository;
import com.example.course_managment.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public CourseDTO createCourse(String name , int unit , Long college_id , Long professor_id) {
        College clg = collegeRepository.findById(college_id)
                .orElseThrow(() -> new CollegeNotFoundException("college with ID" + college_id + "not found !"));
        Professor prof = professorRepository.findById(professor_id)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID" + professor_id + "not found !"));

        Course course = new Course();
        course.setCourse_name(name);
        course.setUnit(unit);
        course.setCollege(clg);
        course.setProfessor(prof);

        Course savedCourse = courseRepository.save(course);
        return CourseMapper.toDTO(savedCourse);
    }

    @Transactional
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public CourseDTO getCourseById(Long id) {
        Course crs = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID" + id + "not found !"));
        return CourseMapper.toDTO(crs);
    }

    @Transactional
    public List<CourseDTO> getCourseByCollegeId(Long college_id) {
        return courseRepository.findByCollegeId(college_id)
                .stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CourseDTO> getCourseByProfessorId(Long professor_id) {
        return courseRepository.findByProfessorId(professor_id)
                .stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public CourseDTO updateCourse(Long id ,String name , int unit ,
                               Long college_id ,
                               Long professor_id ) {
        Course crs = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID" + id + "not found !"));
        College clg = collegeRepository.findById(college_id)
                .orElseThrow(() -> new CollegeNotFoundException("college with ID" + college_id + "not found !"));
        Professor prof = professorRepository.findById(professor_id)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID" + professor_id + "not found !"));

        crs.setCourse_name(name);
        crs.setUnit(unit);
        crs.setCollege(clg);
        crs.setProfessor(prof);

        Course savedCourse = courseRepository.save(crs);
        return CourseMapper.toDTO(savedCourse);
    }

    @Transactional
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

}