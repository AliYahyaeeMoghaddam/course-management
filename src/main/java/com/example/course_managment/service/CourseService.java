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

        Course crs = new Course();
        if(course.getCollege() != null) {
            College clg = collegeRepository.findByName(course.getCollege().getName())
                    .orElseThrow(() -> new CollegeNotFoundException("college with ID " + course.getCollege().getName() + " not found !"));
        crs.setCollege(clg);
        }
        if (course.getProfessor() != null) {
            Professor prof = professorRepository.findById(course.getProfessor().getProf_id())
                    .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID " + course.getProfessor().getProf_id() + " not found !"));

            crs.setProfessor(prof);
        }

        if(course.getCourse_name() != null)
            crs.setCourse_name(course.getCourse_name());
        if(course.getUnit() != 0)
            crs.setUnit(course.getUnit());

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
                .orElseThrow(() -> new CourseNotFoundException("Course with name " + name + " not found !"));
        return CourseMapper.toDTO(crs);
    }

    @Transactional
    public List<CourseDTO> getCourseByCollegeName(String college_name) {
        if (collegeRepository.findByName(college_name).isEmpty())
            throw new CollegeNotFoundException("College with name " + college_name + " not found !");
        return courseRepository.findByCollegeName(college_name)
                .stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CourseDTO> getCourseByProfessorId(Long professor_id) {
        if (professorRepository.findById(professor_id).isEmpty())
            throw new ProfessorNotFoundException("Professor with id " + professor_id + " not found !");
        return courseRepository.findByProfessorId(professor_id)
                .stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public CourseDTO updateCourse(String name ,Course course) {
        Course crs = courseRepository.findByCourseName(name)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + name + " not found !"));
        if (course.getCollege() != null) {
            College clg = collegeRepository.findByName(course.getCollege().getName())
                    .orElseThrow(() -> new CollegeNotFoundException("college with ID " + course.getCollege().getName() + " not found !"));
            crs.setCollege(clg);
        }
        if (course.getProfessor() != null) {
            Professor prof = professorRepository.findById(course.getProfessor().getProf_id())
                    .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID " + course.getProfessor().getProf_id() + " not found !"));
            crs.setProfessor(prof);
        }

        if (course.getCourse_name() != null)
            crs.setCourse_name(course.getCourse_name());
        if (course.getUnit() != 0)
            crs.setUnit(course.getUnit());

        Course savedCourse = courseRepository.save(crs);
        return CourseMapper.toDTO(savedCourse);
    }

    @Transactional
    public void deleteCourse(String name) {
        if(courseRepository.findByCourseName(name).isPresent())
            courseRepository.deleteByCourseName(name);
    }

}