package com.example.course_managment.mapper;

import com.example.course_managment.dto.CourseDTO;
import com.example.course_managment.model.Course;
import com.example.course_managment.model.GradeCourse;
import com.example.course_managment.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public static CourseDTO toDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setCourse_name(course.getCourse_name());
        dto.setUnit(course.getUnit());

        List<Long> studentIds = course.getStudents()
                .stream()
                .map(Student::getStudent_id)
                .collect(Collectors.toList());
        dto.setStudentIds(studentIds);

        dto.setCollegeName(course.getCollege().getName());
        dto.setProfessorId(course.getProfessor().getProf_id());

        List<Long> gradeCourseIds = course.getGradeCourse()
                .stream()
                .map(GradeCourse::getId)
                .collect(Collectors.toList());
        dto.setGradeCourseIds(gradeCourseIds);

        return dto;
    }

}
