package com.example.course_managment.service;

import com.example.course_managment.model.College;
import com.example.course_managment.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {

    private final CollegeRepository collegeRepository;

    @Autowired
    public CollegeService (CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }

    public College createCollege(College college) {
        return collegeRepository.save(college);
    }

    public College getCollegeById(Long id) {
        return collegeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("college not found !"));
    }

    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    public College updateCollege(Long id, College college) {
        College clg = collegeRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("college not found !"));
        clg.setName(college.getName());

        return collegeRepository.save(clg);
    }

    public void deleteCollege(Long id) {
        collegeRepository.deleteById(id);
    }

}
