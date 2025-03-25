package com.example.course_managment.service;

import com.example.course_managment.model.College;
import com.example.course_managment.model.Professor;
import com.example.course_managment.repository.CollegeRepository;
import com.example.course_managment.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {

    private final CollegeRepository collegeRepository;
    private final ProfessorRepository professorRepository;

    @Autowired
    public CollegeService (CollegeRepository collegeRepository , ProfessorRepository professorRepository) {
        this.collegeRepository = collegeRepository;
        this.professorRepository = professorRepository;
    }

    public College createCollege(String name , Long professor_id) {
        Professor prof = professorRepository.findById(professor_id)
                .orElseThrow(() -> new RuntimeException("professor not found !"));

        College college = new College();
        college.setName(name);
        college.setClg_manager(prof);

        return collegeRepository.save(college);
    }

    public College getCollegeById(Long id) {
        return collegeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("college not found !"));
    }

    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    public College getCollegeByName(String name) {
        return collegeRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("college not found !"));
    }

    public College updateCollege(Long id, String name , Long professor_id) {
        College clg = collegeRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("college not found !"));
        Professor prof = professorRepository.findById(professor_id)
                        .orElseThrow(() -> new RuntimeException("professor not found !"));

        clg.setName(name);
        clg.setClg_manager(prof);

        return collegeRepository.save(clg);
    }

    public void deleteCollege(Long id) {
        collegeRepository.deleteById(id);
    }

}