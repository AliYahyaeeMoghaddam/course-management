package com.example.course_managment.controller;

import com.example.course_managment.model.GradeCourse;
import com.example.course_managment.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grade")
public class GradeController {

    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    public ResponseEntity<GradeCourse> addGrade(Long student_id, Long course_id, Long grade) {
        GradeCourse gradeCourse = gradeService.addGrade(student_id, course_id, grade);
        return ResponseEntity.ok(gradeCourse);
    }

}
