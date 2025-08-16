package com.example.course_managment.mapper;

import com.example.course_managment.dto.StudentDTO;
import com.example.course_managment.model.*;
import com.example.course_managment.repository.CollegeRepository;
import com.example.course_managment.repository.CourseRepository;
import com.example.course_managment.repository.GradeRepository;
import com.example.course_managment.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    private static CourseRepository courseRepository;
    private static CollegeRepository collegeRepository;
    private static ProfessorRepository professorRepository;
    private static GradeRepository gradeCourseRepository;

    @Autowired
    public StudentMapper(CourseRepository courseRepository, CollegeRepository collegeRepository,
                         ProfessorRepository professorRepository, GradeRepository gradeCourseRepository) {
        this.courseRepository = courseRepository;
        this.collegeRepository = collegeRepository;
        this.professorRepository = professorRepository;
        this.gradeCourseRepository = gradeCourseRepository;
    }

    public static StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setStudent_id(student.getStudent_id());
        dto.setStudent_name(student.getStudent_name());
        dto.setStudent_lastName(student.getStudent_lastName());

        if(!student.getCourses().isEmpty()) {
            List<String> courseNames = student.getCourses()
                    .stream()
                    .map(Course::getCourse_name)
                    .collect(Collectors.toList());
            dto.setCourseNames(courseNames);
        }

        if(student.getClg() != null)
            dto.setClgName(student.getClg().getName());

        if(!student.getProfessors().isEmpty()) {
            List<Long> professorIds = student.getProfessors()
                    .stream()
                    .map(Professor::getProf_id)
                    .collect(Collectors.toList());
            dto.setProfessorIds(professorIds);
        }

        if(!student.getGradeCourses().isEmpty()) {
            List<Long> gradeIds = student.getGradeCourses()
                    .stream()
                    .map(GradeCourse::getId)
                    .collect(Collectors.toList());
            dto.setGradeCourseIds(gradeIds);
        }

        return dto;
    }

    public static Student toEntity(StudentDTO dto) {
        if (dto == null) return null;

        Student student = new Student();
        student.setStudent_id(dto.getStudent_id());
        student.setStudent_name(dto.getStudent_name());
        student.setStudent_lastName(dto.getStudent_lastName());

        if (dto.getCourseNAmes() != null) {
            List<Course> courses = dto.getCourseNAmes()
                    .stream()
                    .map(courseRepository::findByCourseName)
                    .collect(Collectors.toList());
            student.setCourses(courses);
        }

        if (dto.getClgName() != null) {
            Optional<College> clg = collegeRepository.findByName(dto.getClgName());
            student.setClg(clg.orElse(null));
        }

        if (dto.getProfessorIds() != null) {
            List<Professor> professors = professorRepository.findAllById(dto.getProfessorIds());
            student.setProfessors(professors);
        }

        if (dto.getGradeCourseIds() != null) {
            List<GradeCourse> gradeCourses = gradeCourseRepository.findAllById(dto.getGradeCourseIds());
            student.setGradeCourses(gradeCourses);
        }

        return student;
    }
}
