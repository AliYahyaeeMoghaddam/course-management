package com.example.course_managment.mapper;

import com.example.course_managment.dto.CollegeDTO;
import com.example.course_managment.dto.ProfessorSimpleDTO;
import com.example.course_managment.model.College;
import com.example.course_managment.model.Course;
import com.example.course_managment.model.Professor;
import com.example.course_managment.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CollegeMapper {

    public static CollegeDTO toDTO(College college){
        CollegeDTO dto = new CollegeDTO();
        dto.setName(college.getName());

        Professor professor = college.getClg_manager();
        ProfessorSimpleDTO professorSimpleDTO = new ProfessorSimpleDTO();
        professorSimpleDTO.setProf_id(professor.getProf_id());
        professorSimpleDTO.setProf_name(professor.getProf_name());
        professorSimpleDTO.setProf_lastName(professor.getProf_lastName());
        dto.setClg_manager(professorSimpleDTO);

        List<Long> studentIds = college.getStudents()
                .stream()
                .map(Student::getStudent_id)
                .collect(Collectors.toList());
        dto.setStudents(studentIds);

        List<String> courseNames = college.getCourses()
                .stream()
                .map(Course::getCourse_name)
                .collect(Collectors.toList());
        dto.setCourses(courseNames);

        List<Long> professorIds = college.getProfessors()
                .stream()
                .map(Professor::getProf_id)
                .collect(Collectors.toList());
        dto.setProfessors(professorIds);

        return dto;
    }

}
