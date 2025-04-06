package com.example.course_managment.mapper;

import com.example.course_managment.dto.GradeCourseDTO;
import com.example.course_managment.model.GradeCourse;
import org.springframework.stereotype.Component;

@Component
public class GradeCourseMapper {

    public static GradeCourseDTO toDTO(GradeCourse gradeCourse) {
        GradeCourseDTO dto = new GradeCourseDTO();
        dto.setId(gradeCourse.getId());
        dto.setStudentId(gradeCourse.getStudent().getStudent_id());
        dto.setCourseName(gradeCourse.getCourse().getCourse_name());
        dto.setGarde(gradeCourse.getGarde());

        return dto;
    }

}
