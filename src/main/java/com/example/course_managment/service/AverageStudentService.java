package com.example.course_managment.service;


import com.example.course_managment.dto.AverageStudentDTO;
import com.example.course_managment.mapper.AverageStudentMapper;
import com.example.course_managment.model.AverageStudent;
import com.example.course_managment.repository.AverageStudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AverageStudentService {

    private final AverageStudentRepository averageStudentRepository;

    @Autowired
    public AverageStudentService(AverageStudentRepository averageStudentRepository) {
        this.averageStudentRepository = averageStudentRepository;
    }

    @Transactional
    public AverageStudentDTO setAverageStudent(AverageStudent averageStudent) {
        AverageStudent average = new AverageStudent();
        double ave = 0.0;
        double sum = 0.0;
        for (var grade : averageStudent.getStudent().getCourses()) {
            sum = sum + grade.getGradeCourse().getGarde();
        }
        int count = averageStudent.getStudent().getCourses().size();
        ave = sum / count;

        average.setId(averageStudent.getId());
        average.setAverage(ave);
        average.setStudent(averageStudent.getStudent());

        averageStudentRepository.save(average);
        return AverageStudentMapper.toDTO(average);
    }
    @Transactional
    public AverageStudentDTO getAverageStudent(Long stud_id) {
        AverageStudent ave = averageStudentRepository.findByStudentId(stud_id)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));
        return AverageStudentMapper.toDTO(ave);
    }

}
