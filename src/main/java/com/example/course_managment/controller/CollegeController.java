package com.example.course_managment.controller;

import com.example.course_managment.dto.CollegeDTO;
import com.example.course_managment.dto.ProfessorDTO;
import com.example.course_managment.model.College;
import com.example.course_managment.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> createCollege(@RequestBody College college) {
        CollegeDTO clg = collegeService.createCollege(college);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CollegeDTO>> getAllColleges() {
        return ResponseEntity.ok(collegeService.getAllColleges());
    }

    @GetMapping("/{name}")
    public ResponseEntity<CollegeDTO>  getCollegeByName(@PathVariable String name){
        CollegeDTO clg = collegeService.getCollegeByName(name);
        return ResponseEntity.ok(clg);
    }


    @PutMapping("/{name}")
    public ResponseEntity<CollegeDTO> updateCollege(@PathVariable String name,
                                                 @RequestBody College college) {
        CollegeDTO clg = collegeService.updateCollege(name, college);
        return ResponseEntity.ok(clg);
    }

    @DeleteMapping("/{name}")
    public void deleteCollegeByName(@PathVariable String name){
        collegeService.deleteCollege(name);
    }

    @PostMapping("/{name}/manager/{id}")
    public ResponseEntity<CollegeDTO> setManager(@PathVariable String name, @PathVariable Long id){
        CollegeDTO clg = collegeService.setCollegeManager(id,name);
        return ResponseEntity.ok(clg);
    }

    @GetMapping("/{name}/manager")
    public ResponseEntity<ProfessorDTO> getManager(@PathVariable String name){
        ProfessorDTO manager = collegeService.getManager(name);
        return ResponseEntity.ok(manager);
    }

    @DeleteMapping("/{name}/manager")
    public void deleteManager(@PathVariable String name){
        collegeService.deleteManager(name);
    }
}