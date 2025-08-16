package com.example.course_managment.controller;

import com.example.course_managment.features.gpa.ParallelGpaCalculator;
import com.example.course_managment.model.Student;
import com.example.course_managment.repository.StudentRepository;
import com.example.course_managment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gpa")
public class GPAController {

    private final ParallelGpaCalculator calculator;
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @Autowired
    public GPAController(ParallelGpaCalculator calculator, StudentService studentService, StudentRepository studentRepository) {
        this.calculator = calculator;
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public ResponseEntity<String> getGPA() throws InterruptedException {
        List<Student> students = studentRepository.findAll();
        calculator.service(students);
        return ResponseEntity.ok("GPA calculation started!");
    }

}
