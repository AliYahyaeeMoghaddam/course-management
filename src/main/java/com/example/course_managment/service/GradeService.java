package com.example.course_managment.service;

import com.example.course_managment.dto.GradeCourseDTO;
import com.example.course_managment.mapper.GradeCourseMapper;
import com.example.course_managment.model.GradeCourse;
import com.example.course_managment.repository.GradeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Transactional
    public GradeCourseDTO addGrade(GradeCourse gradeCourse) {
        Optional<GradeCourse> gradeCrs = gradeRepository.findByStudentId(gradeCourse.getStudent().getStudent_id());

        if (gradeCrs.isEmpty()) {
            throw new RuntimeException("grade course not found");
        }

        GradeCourse grade_crs = gradeCrs.get();
        grade_crs.setGarde(grade_crs.getGarde());
        GradeCourse savedGrade = gradeRepository.save(grade_crs);

        return GradeCourseMapper.toDTO(savedGrade);
    }

}
