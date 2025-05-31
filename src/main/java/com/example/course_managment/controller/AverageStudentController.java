package com.example.course_managment.controller;

import com.example.course_managment.dto.AverageStudentDTO;
import com.example.course_managment.model.AverageStudent;
import com.example.course_managment.service.AverageStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/average")
public class AverageStudentController {

    private final AverageStudentService averageStudentService;

    @Autowired
    public AverageStudentController(AverageStudentService averageStudentService) {
        this.averageStudentService = averageStudentService;
    }

    @PostMapping()
    public ResponseEntity<AverageStudentDTO> createAverageStudent(@RequestBody AverageStudent averageStudent) {
        AverageStudentDTO averageStudentDTO = averageStudentService.setAverageStudent(averageStudent);
        return ResponseEntity.ok(averageStudentDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AverageStudentDTO> getAverageStudent(@PathVariable Long id) {
        AverageStudentDTO averageStudentDTO = averageStudentService.getAverageStudent(id);
        return ResponseEntity.ok(averageStudentDTO);
    }

}