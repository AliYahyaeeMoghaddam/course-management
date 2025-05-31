package com.example.course_managment.mapper;

import com.example.course_managment.dto.AverageStudentDTO;
import com.example.course_managment.model.AverageStudent;
import org.springframework.stereotype.Component;

@Component
public class AverageStudentMapper {

    public static AverageStudentDTO toDTO(AverageStudent averageStudent) {

        AverageStudentDTO dto = new AverageStudentDTO();
        dto.setId(averageStudent.getId());
        dto.setAverage(averageStudent.getAverage());
        dto.setStudentName(averageStudent.getStudent().getStudent_name());

        return dto;
    }

}
