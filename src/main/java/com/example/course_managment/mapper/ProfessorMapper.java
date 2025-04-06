package com.example.course_managment.mapper;

import com.example.course_managment.dto.ProfessorDTO;
import com.example.course_managment.model.Course;
import com.example.course_managment.model.Professor;
import com.example.course_managment.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfessorMapper {

    public static ProfessorDTO toDTO(Professor professor) {

        ProfessorDTO dto = new ProfessorDTO();
        dto.setProf_id(professor.getProf_id());
        dto.setProf_name(professor.getProf_name());
        dto.setProf_lastName(professor.getProf_lastName());
        dto.setCollege(professor.getCollege().getName());

        List<Long> studentIds = professor.getStudents()
                .stream()
                .map(Student::getStudent_id)
                .collect(Collectors.toList());
        dto.setStudentIds(studentIds);

        List<String> courseNames = professor.getCourses()
                .stream()
                .map(Course::getCourse_name)
                .collect(Collectors.toList());
        dto.setCourseNames(courseNames);

        return dto;
    }

}
