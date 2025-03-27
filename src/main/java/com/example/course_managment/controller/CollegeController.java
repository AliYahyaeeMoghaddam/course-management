package com.example.course_managment.controller;

import com.example.course_managment.model.College;
import com.example.course_managment.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colleges")
public class CollegeController {

    private final CollegeService collegeService;

    @Autowired
    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @PostMapping
    public ResponseEntity<College> createCollege(@RequestParam String name, @RequestParam Long prof_id) {
        College clg = collegeService.createCollege(name, prof_id);
        return ResponseEntity.ok(clg);
    }

    @GetMapping
    public ResponseEntity<List<College>> getAllColleges() {
        return ResponseEntity.ok(collegeService.getAllColleges());
    }

    @GetMapping("/{id}")
    public ResponseEntity<College>  getCollegeById(@PathVariable Long id){
        College clg = collegeService.getCollegeById(id);
        return ResponseEntity.ok(clg);
    }

    @GetMapping("/{name}")
    public ResponseEntity<College> getCollegeByName(@PathVariable String name) {
        College clg = collegeService.getCollegeByName(name);
        return ResponseEntity.ok(clg);
    }

    @PutMapping("/{id}")
    public ResponseEntity<College> updateCollege(@PathVariable Long id,
                                                 @RequestParam String name,
                                                 @RequestParam Long prof_id){
        College clg = collegeService.updateCollege(id, name, prof_id);
        return ResponseEntity.ok(clg);
    }

    @DeleteMapping("/{id}")
    public void deleteCollegeById(@PathVariable Long id){
        collegeService.deleteCollege(id);
    }

}
