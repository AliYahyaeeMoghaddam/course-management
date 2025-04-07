package com.example.course_managment.service;

import com.example.course_managment.dto.CollegeDTO;
import com.example.course_managment.exception.CollegeNotFoundException;
import com.example.course_managment.exception.ProfessorNotFoundException;
import com.example.course_managment.mapper.CollegeMapper;
import com.example.course_managment.model.College;
import com.example.course_managment.model.Professor;
import com.example.course_managment.repository.CollegeRepository;
import com.example.course_managment.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollegeService {

    private final CollegeRepository collegeRepository;
    private final ProfessorRepository professorRepository;

    @Autowired
    public CollegeService (CollegeRepository collegeRepository , ProfessorRepository professorRepository) {
        this.collegeRepository = collegeRepository;
        this.professorRepository = professorRepository;
    }

    @Transactional
    public CollegeDTO createCollege(String name, Long prof_id) {
        Professor prof = professorRepository.findById(prof_id)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID " + prof_id + " not found !"));

        College college = new College();
        college.setName(name);
        college.setClg_manager(prof);

        College savedCollege = collegeRepository.save(college);
        return CollegeMapper.toDTO(savedCollege);
    }

    @Transactional
    public CollegeDTO getCollegeByName(String name) {
        College college = collegeRepository.findByName(name)
                .orElseThrow(() -> new CollegeNotFoundException("college with ID" + name + "not found !"));
        return CollegeMapper.toDTO(college);
    }

    @Transactional
    public List<CollegeDTO> getAllColleges() {
        return collegeRepository.findAll()
                .stream()
                .map(CollegeMapper::toDTO)
                .collect(Collectors.toList());
    }

//    @Transactional
//    public CollegeDTO getCollegeByName(String name) {
//        College college = collegeRepository.findByName(name)
//                .orElseThrow(() -> new CollegeNotFoundException("college with name" + name + "not found !"));
//        return CollegeMapper.toDTO(college);
//    }

    @Transactional
    public CollegeDTO updateCollege(String name, String newName , Long professor_id) {
        College clg = collegeRepository.findByName(name)
                        .orElseThrow(() -> new CollegeNotFoundException("college with ID" + name + "not found !"));
        Professor prof = professorRepository.findById(professor_id)
                        .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID" + professor_id + "not found !"));

        clg.setName(name);
        clg.setClg_manager(prof);

        College savedCollege = collegeRepository.save(clg);
        return CollegeMapper.toDTO(savedCollege);
    }

    @Transactional
    public void deleteCollege(String name) {
        collegeRepository.deleteByName(name);
    }

}