package com.example.course_managment.mapper;

import com.example.course_managment.dto.AverageProfessorDTO;
import com.example.course_managment.model.AverageProfessor;

public class AverageProfessorMapper {

    public static AverageProfessorDTO toDTO(AverageProfessor averageProfessor) {

        AverageProfessorDTO dto = new AverageProfessorDTO();

        if(averageProfessor.getProfessorId() != null)
            dto.setProfessorId(averageProfessor.getProfessorId());
        if(averageProfessor.getCourseName() != null)
            dto.setCourseName(averageProfessor.getCourseName());
        if(averageProfessor.getAverage() != null)
            dto.setAverage(averageProfessor.getAverage());

        return dto;
    }

}
