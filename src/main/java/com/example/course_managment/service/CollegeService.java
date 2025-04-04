package com.example.course_managment.service;

import com.example.course_managment.dto.CollegeDTO;
import com.example.course_managment.dto.StudentDTO;
import com.example.course_managment.exception.CollegeNotFoundException;
import com.example.course_managment.exception.ProfessorNotFoundException;
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
                .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID" + professor_id + "not found !"));

        College college = new College();
        college.setName(name);
        college.setClg_manager(prof);

        return collegeRepository.save(college);
    }

    public CollegeDTO getCollegeById(Long id) {
        College college = collegeRepository.findById(id)
                .orElseThrow(() -> new CollegeNotFoundException("college with ID" + id + "not found !"));
//        List<StudentDTO> students = college.getStudents().stream()
//                .map()
    }

    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    public College getCollegeByName(String name) {
        return collegeRepository.findByName(name)
                .orElseThrow(() -> new CollegeNotFoundException("college with name" + name + "not found !"));
    }

    public College updateCollege(Long id, String name , Long professor_id) {
        College clg = collegeRepository.findById(id)
                        .orElseThrow(() -> new CollegeNotFoundException("college with ID" + id + "not found !"));
        Professor prof = professorRepository.findById(professor_id)
                        .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID" + professor_id + "not found !"));

        clg.setName(name);
        clg.setClg_manager(prof);

        return collegeRepository.save(clg);
    }

    public void deleteCollege(Long id) {
        collegeRepository.deleteById(id);
    }

}