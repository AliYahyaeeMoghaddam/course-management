package com.example.course_managment.mapper;

import com.example.course_managment.dto.AverageCollegeDTO;
import com.example.course_managment.model.AverageCollege;
import com.example.course_managment.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AverageCollegeMapper {
    public static AverageCollegeDTO toDTO(AverageCollege averageCollege){
        AverageCollegeDTO dto = new AverageCollegeDTO();

//        if(averageCollege.getId() != null)
//            dto.setId(averageCollege.getId());
        if(averageCollege.getCollegeName() != null)
            dto.setCollegeName(averageCollege.getCollegeName());

//        if(!averageCollege.getStudents().isEmpty()) {
//            List<Long> studentIds = averageCollege.getStudents()
//                    .stream()
//                    .map(Student::getStudent_id)
//                    .collect(Collectors.toList());
//            dto.setStudent_names(studentIds);
//        }

        if(averageCollege.getAverage() != null)
            dto.setAverage_college(averageCollege.getAverage());

        return dto;
    }
}
