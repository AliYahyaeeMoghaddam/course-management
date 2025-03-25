package com.example.course_managment.service;

import com.example.course_managment.model.College;
import com.example.course_managment.model.Professor;
import com.example.course_managment.model.Student;
import com.example.course_managment.repository.CollegeRepository;
import com.example.course_managment.repository.ProfessorRepository;
import com.example.course_managment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final CollegeRepository collegeRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository ,
                            CollegeRepository collegeRepository) {
        this.professorRepository = professorRepository;
        this.collegeRepository = collegeRepository;
    }

    public Professor createProfessor(String firstName, String lastName,
                                     Long national_code, Long college_id) {
        College clg = collegeRepository.findById(college_id)
                .orElseThrow(() -> new RuntimeException("College not found"));

        Professor professor = new Professor();
        professor.setProf_name(firstName);
        professor.setProf_lastName(lastName);
        professor.setNational_code(national_code);
        professor.setCollege(clg);

        return professorRepository.save(professor);
    }

    public List<Professor> getAllProfessors(){
        return professorRepository.findAll();
    }

    public Professor getProfessorById(Long id){
        return professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor Not Found"));
    }

    public List<Professor> getProfessorsByCollegeId(Long college_id){
        return professorRepository.findByCollegeId(college_id);
    }

    public Professor updateProfessor(Long id, String firstName, String lastName,
                                     Long national_code, Long college_id) {
        Professor prof = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor Not Found"));
        College clg = collegeRepository.findById(college_id)
                .orElseThrow(() -> new RuntimeException("College not found"));

        prof.setProf_name(firstName);
        prof.setProf_lastName(lastName);
        prof.setNational_code(national_code);
        prof.setCollege(clg);

        return professorRepository.save(prof);
    }

    public void deleteProfessor(Long id){
        professorRepository.deleteById(id);
    }

}
