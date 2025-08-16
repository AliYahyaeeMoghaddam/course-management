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

        if (gradeCourse.getStudent() == null || gradeCourse.getCourse() == null) {
            throw new RuntimeException("Student or Course is required");
        }

        Optional<GradeCourse> existing = gradeRepository.findByStudentIdAndCourseCourseName(
                gradeCourse.getStudent().getStudent_id(),
                gradeCourse.getCourse().getCourse_name()
        );

        GradeCourse gradeCrs;
        if (existing.isPresent()) {
            gradeCrs = existing.get();
            gradeCrs.setGarde(gradeCourse.getGarde());
        } else {
            gradeCrs = new GradeCourse();
            gradeCrs.setStudent(gradeCourse.getStudent());
            gradeCrs.setCourse(gradeCourse.getCourse());
            gradeCrs.setGarde(gradeCourse.getGarde());
        }

        GradeCourse savedGrade = gradeRepository.save(gradeCrs);
        return GradeCourseMapper.toDTO(savedGrade);
    }


}
