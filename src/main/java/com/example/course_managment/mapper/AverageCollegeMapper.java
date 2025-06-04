package com.example.course_managment.mapper;

import com.example.course_managment.dto.AverageCollegeDTO;
import com.example.course_managment.model.AverageCollege;

public class AverageCollegeMapper {
    public static AverageCollegeDTO toDTO(AverageCollege averageCollege){
        AverageCollegeDTO dto = new AverageCollegeDTO();

        if(averageCollege.getCollegeName() != null)
            dto.setCollegeName(averageCollege.getCollegeName());

        if(averageCollege.getAverage() != null)
            dto.setAverage_college(averageCollege.getAverage());

        return dto;
    }
}
