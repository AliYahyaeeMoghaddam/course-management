package com.example.course_managment.service;

import com.example.course_managment.dto.CollegeDTO;
import com.example.course_managment.dto.CreateCollegeDTO;
import com.example.course_managment.dto.StudentDTO;
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

import java.util.ArrayList;
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
    public CollegeDTO createCollege(CreateCollegeDTO createCollegeDTO) {
        Professor prof = professorRepository.findById(createCollegeDTO.getProf_managerId())
                .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID " + createCollegeDTO.getProf_managerId() + " not found !"));

        College college = new College();
        college.setName(createCollegeDTO.getName());
        college.setClg_manager(prof);

        College savedCollege = collegeRepository.save(college);
        return CollegeMapper.toDTO(savedCollege);
    }

    @Transactional
    public CollegeDTO getCollegeById(Long id) {
        College college = collegeRepository.findById(id)
                .orElseThrow(() -> new CollegeNotFoundException("college with ID" + id + "not found !"));
        return CollegeMapper.toDTO(college);
    }

    @Transactional
    public List<CollegeDTO> getAllColleges() {
        return collegeRepository.findAll()
                .stream()
                .map(CollegeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public CollegeDTO getCollegeByName(String name) {
        College college = collegeRepository.findByName(name)
                .orElseThrow(() -> new CollegeNotFoundException("college with name" + name + "not found !"));
        return CollegeMapper.toDTO(college);
    }

    @Transactional
    public CollegeDTO updateCollege(Long id, String name , Long professor_id) {
        College clg = collegeRepository.findById(id)
                        .orElseThrow(() -> new CollegeNotFoundException("college with ID" + id + "not found !"));
        Professor prof = professorRepository.findById(professor_id)
                        .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID" + professor_id + "not found !"));

        clg.setName(name);
        clg.setClg_manager(prof);

        College savedCollege = collegeRepository.save(clg);
        return CollegeMapper.toDTO(savedCollege);
    }

    @Transactional
    public void deleteCollege(Long id) {
        collegeRepository.deleteById(id);
    }

}