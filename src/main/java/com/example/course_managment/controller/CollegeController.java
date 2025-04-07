package com.example.course_managment.controller;

import com.example.course_managment.dto.CollegeDTO;
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
    public ResponseEntity<CollegeDTO> createCollege(@RequestParam String name, @RequestParam Long prof_id) {
        CollegeDTO clg = collegeService.createCollege(name, prof_id);
        return ResponseEntity.ok(clg);
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

//    @GetMapping("/{name}")
//    public ResponseEntity<CollegeDTO> getCollegeByName(@PathVariable String name) {
//        CollegeDTO clg = collegeService.getCollegeByName(name);
//        return ResponseEntity.ok(clg);
//    }

    @PutMapping("/{name}")
    public ResponseEntity<CollegeDTO> updateCollege(@PathVariable String name,
                                                 @RequestParam String newName,
                                                 @RequestParam Long prof_id){
        CollegeDTO clg = collegeService.updateCollege(name, newName, prof_id);
        return ResponseEntity.ok(clg);
    }

    @DeleteMapping("/{name}")
    public void deleteCollegeByName(@PathVariable String name){
        collegeService.deleteCollege(name);
    }

}
