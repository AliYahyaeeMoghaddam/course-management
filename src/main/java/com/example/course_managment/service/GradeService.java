package com.example.course_managment.service;

import com.example.course_managment.model.GradeCourse;
import com.example.course_managment.repository.GradeRepository;
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

    public GradeCourse addGrade(Long student_id, Long course_id, Long grade) {
        Optional<GradeCourse> gradeCourse = gradeRepository.findByStudentIdAndCourseId(student_id,course_id);

        if (!gradeCourse.isPresent()) {
            throw new RuntimeException("grade course not found");
        }

        GradeCourse grade_crs = gradeCourse.get();
        grade_crs.setGarde(grade);
        return gradeRepository.save(grade_crs);
    }

}
