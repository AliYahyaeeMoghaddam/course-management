package com.example.course_managment.controller;

import com.example.course_managment.dto.AverageCollegeDTO;
import com.example.course_managment.service.AverageCollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Average_College")
public class AverageCollegeController {

    final private AverageCollegeService averageCollegeService;

    @Autowired
    public AverageCollegeController(AverageCollegeService averageCollegeService) {
        this.averageCollegeService = averageCollegeService;
    }

    @GetMapping("/{college_name}")
    public ResponseEntity<AverageCollegeDTO> getAverageCollege(@PathVariable String college_name) {
        AverageCollegeDTO ave = new AverageCollegeDTO();
        ave = averageCollegeService.setAverageCollege(college_name);
        return ResponseEntity.ok(ave);
    }

}
