package com.example.course_managment.controller;

import com.example.course_managment.model.Professor;
import com.example.course_managment.model.Student;
import com.example.course_managment.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestParam String firstName, @RequestParam String lastName,
                                                     @RequestParam Long national_code, @RequestParam Long college_id) {
        Professor prof = professorService.createProfessor(firstName, lastName, national_code, college_id);
        return ResponseEntity.ok(prof);
    }

    @GetMapping
    public ResponseEntity<List<Professor>> getAllProfessor() {
        List<Professor> profs = professorService.getAllProfessors();
        return ResponseEntity.ok(profs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable long id) {
        Professor prof = professorService.getProfessorById(id);
        return ResponseEntity.ok(prof);
    }

    @GetMapping("/{college_id}")
    public ResponseEntity<List<Professor>> getProfessorByCollegeId(@PathVariable long college_id) {
        List<Professor> profs = professorService.getProfessorsByCollegeId(college_id);
        return ResponseEntity.ok(profs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @RequestParam String firstName,
                                                     @RequestParam String lastName,
                                                     @RequestParam Long national_code,
                                                     @RequestParam Long college_id){
        Professor prof = professorService.updateProfessor(id, firstName, lastName, national_code, college_id);
        return ResponseEntity.ok(prof);
    }

    @DeleteMapping("/{id}")
    public void deleteProfessor(@PathVariable long id) {
        professorService.deleteProfessor(id);
    }

    @PostMapping("/{prof_id}/students/{student_id}")
    public ResponseEntity<String> AddingStudentByProfessor(@PathVariable Long prof_id,
                                                           @PathVariable Long student_id){
        professorService.AddingStudentByProfessor(prof_id, student_id);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/{prof_id}/students/{student_id}")
    public ResponseEntity<String> DeleteStudentByProfessor(@PathVariable Long prof_id, @PathVariable Long student_id){
        professorService.DeleteStudentByProfessor(prof_id, student_id);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/{prof_id}/students")
    public ResponseEntity<List<Student>> getStudentsOfProfessor(@PathVariable Long prof_id){
        List<Student> students = professorService.getStudentsOfProfessor(prof_id);
        return ResponseEntity.ok(students);
    }

}
