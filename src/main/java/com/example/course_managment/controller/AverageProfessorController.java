package com.example.course_managment.controller;

import com.example.course_managment.dto.AverageProfessorDTO;
import com.example.course_managment.service.AverageProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Average_Professor")
public class AverageProfessorController {

    final private AverageProfessorService averageProfessorService;
    
    @Autowired
    public AverageProfessorController (AverageProfessorService averageProfessorService){
        this.averageProfessorService = averageProfessorService;
    }

    @GetMapping("/{prof_id}/{course_name}")
    public ResponseEntity<AverageProfessorDTO> getAverageProfessor(@PathVariable Long prof_id, @PathVariable String course_name){
        AverageProfessorDTO ave = averageProfessorService.setAverageProfessor(prof_id, course_name);
        return ResponseEntity.ok(ave);
    }

}
