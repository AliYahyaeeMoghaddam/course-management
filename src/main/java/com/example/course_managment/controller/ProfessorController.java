package com.example.course_managment.controller;

import com.example.course_managment.dto.ProfessorDTO;
import com.example.course_managment.dto.StudentDTO;
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
    public ResponseEntity<ProfessorDTO> createProfessor(@RequestBody Professor professor) {
        ProfessorDTO prof = professorService.createProfessor(professor);
        return ResponseEntity.ok(prof);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> getAllProfessor() {
        List<ProfessorDTO> profs = professorService.getAllProfessors();
        return ResponseEntity.ok(profs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> getProfessorById(@PathVariable long id) {
        ProfessorDTO prof = professorService.getProfessorById(id);
        return ResponseEntity.ok(prof);
    }

    @GetMapping("/{college_name}")
    public ResponseEntity<List<ProfessorDTO>> getProfessorByCollegeId(@PathVariable String college_name) {
        List<ProfessorDTO> profs = professorService.getProfessorsByCollegeName(college_name);
        return ResponseEntity.ok(profs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDTO> updateProfessor(@PathVariable Long id, @RequestBody Professor professor) {
        ProfessorDTO prof = professorService.updateProfessor(id, professor);
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
    public ResponseEntity<List<StudentDTO>> getStudentsOfProfessor(@PathVariable Long prof_id){
        List<StudentDTO> students = professorService.getStudentsOfProfessor(prof_id);
        return ResponseEntity.ok(students);
    }

}
