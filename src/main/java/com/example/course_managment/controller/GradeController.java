package com.example.course_managment.controller;

import com.example.course_managment.dto.GradeCourseDTO;
import com.example.course_managment.mapper.GradeCourseMapper;
import com.example.course_managment.model.GradeCourse;
import com.example.course_managment.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<GradeCourseDTO> addGrade(@RequestBody GradeCourse gradeCourse) {
        GradeCourseDTO gradeCrs = gradeService.addGrade(gradeCourse);
        return ResponseEntity.ok(gradeCrs);
    }

}
