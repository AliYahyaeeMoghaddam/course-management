package com.example.course_managment.service;

import com.example.course_managment.dto.CollegeDTO;
import com.example.course_managment.dto.ProfessorDTO;
import com.example.course_managment.exception.CollegeNotFoundException;
import com.example.course_managment.exception.ProfessorNotFoundException;
import com.example.course_managment.exception.TheCollegeHasAManager;
import com.example.course_managment.mapper.CollegeMapper;
import com.example.course_managment.mapper.ProfessorMapper;
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
    public CollegeDTO createCollege(College college) {

        College clg = new College();
        if(college.getName() != null)
            clg.setName(college.getName());

        if(college.getClg_manager() != null){
            Professor prof = professorRepository.findById(college.getClg_manager().getProf_id())
                    .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID " + college.getClg_manager().getProf_id() + " not found !"));

            clg.setClg_manager(prof);
        }

        College savedCollege = collegeRepository.save(clg);
        return CollegeMapper.toDTO(savedCollege);
    }

    @Transactional
    public CollegeDTO getCollegeByName(String name) {
            College college = collegeRepository.findByName(name)
                    .orElseThrow(() -> new CollegeNotFoundException("college with ID " + name + " not found !"));
            return CollegeMapper.toDTO(college);

    }

    @Transactional
    public List<CollegeDTO> getAllColleges() {
        if(collegeRepository.findAll().isEmpty()) {
            throw new CollegeNotFoundException("No college found !");
        }
        return collegeRepository.findAll()
                .stream()
                .map(CollegeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public CollegeDTO updateCollege(String name, College college) {
        College clg = collegeRepository.findByName(name)
                        .orElseThrow(() -> new CollegeNotFoundException("college with ID " + name + " not found !"));
        if (college.getClg_manager() != null) {
            Professor prof = professorRepository.findById(college.getClg_manager().getProf_id())
                    .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID " + college.getClg_manager().getProf_id() + " not found !"));

            clg.setClg_manager(prof);
        }

        if(college.getName() != null)
            clg.setName(college.getName());

        College savedCollege = collegeRepository.save(clg);
        return CollegeMapper.toDTO(savedCollege);
    }

    @Transactional
    public void deleteCollege(String name) {
        if(collegeRepository.findByName(name).isPresent())
            collegeRepository.deleteByName(name);
    }

    @Transactional
    public CollegeDTO setCollegeManager(Long prof_id , String college_name) {
        if(professorRepository.findById(prof_id).isEmpty())
            throw new ProfessorNotFoundException("Professor with ID " + prof_id + " not found !");
        if(collegeRepository.findByName(college_name).isEmpty())
            throw new CollegeNotFoundException("College with name " + college_name + " not found !");

        Professor manager = professorRepository.findById(prof_id).get();
        College college = collegeRepository.findByName(college_name).get();

        if(college.getClg_manager() == null)
            college.setClg_manager(manager);
        else
            throw new TheCollegeHasAManager("The college has a manager!");
        return CollegeMapper.toDTO(collegeRepository.save(college));
    }

    @Transactional
    public ProfessorDTO getManager(String college_name) {
        if(collegeRepository.findByName(college_name).isPresent()){
            College college = collegeRepository.findByName(college_name).get();
            if(college.getClg_manager() != null) {
                Professor manager = college.getClg_manager();
                return ProfessorMapper.toDTO(manager);
            }
            else
                throw new ProfessorNotFoundException("The college does not have a manager!");
        }
        throw new CollegeNotFoundException("College with name " + college_name + " not found !");
    }

    @Transactional
    public void deleteManager(String college_name) {
        if(collegeRepository.findByName(college_name).isEmpty())
            throw new CollegeNotFoundException("College with name " + college_name + " not found !");
        College clg = collegeRepository.findByName(college_name).get();
        clg.setClg_manager(null);
        collegeRepository.save(clg);
    }

}