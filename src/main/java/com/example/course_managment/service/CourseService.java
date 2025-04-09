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
    public CourseDTO createCourse(Course course) {
        College clg = collegeRepository.findByName(course.getCollege().getName())
                .orElseThrow(() -> new CollegeNotFoundException("college with ID" + course.getCollege().getName() + "not found !"));
        Professor prof = professorRepository.findById(course.getProfessor().getProf_id())
                .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID" + course.getProfessor().getProf_id() + "not found !"));

        Course crs = new Course();
        crs.setCourse_name(course.getCourse_name());
        crs.setUnit(course.getUnit());
        crs.setCollege(clg);
        crs.setProfessor(prof);

        Course savedCourse = courseRepository.save(crs);
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
    public CourseDTO getCourseByName(String name) {
        Course crs = courseRepository.findByCourseName(name)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID" + name + "not found !"));
        return CourseMapper.toDTO(crs);
    }

    @Transactional
    public List<CourseDTO> getCourseByCollegeName(String college_name) {
        return courseRepository.findByCollegeName(college_name)
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
    public CourseDTO updateCourse(String name ,Course course) {
        Course crs = courseRepository.findByCourseName(name)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID" + name + "not found !"));
        College clg = collegeRepository.findByName(course.getCollege().getName())
                .orElseThrow(() -> new CollegeNotFoundException("college with ID" + course.getCollege().getName() + "not found !"));
        Professor prof = professorRepository.findById(course.getProfessor().getProf_id())
                .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID" + course.getProfessor().getProf_id() + "not found !"));

        crs.setCourse_name(course.getCourse_name());
        crs.setUnit(course.getUnit());
        crs.setCollege(clg);
        crs.setProfessor(prof);

        Course savedCourse = courseRepository.save(crs);
        return CourseMapper.toDTO(savedCourse);
    }

    @Transactional
    public void deleteCourse(String name) {
        courseRepository.deleteByCourseName(name);
    }

}