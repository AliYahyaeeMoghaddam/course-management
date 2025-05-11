package com.example.course_managment.mapper;

import com.example.course_managment.dto.StudentDTO;
import com.example.course_managment.model.Course;
import com.example.course_managment.model.GradeCourse;
import com.example.course_managment.model.Professor;
import com.example.course_managment.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

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

}
