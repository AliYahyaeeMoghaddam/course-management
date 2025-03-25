package com.example.course_managment.service;

import com.example.course_managment.model.Professor;
import com.example.course_managment.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor createProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public List<Professor> getAllStudents(){
        return professorRepository.findAll();
    }

    public Professor getProfessorById(Long id){
        return professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor Not Found"));
    }

    public Professor updateProfessor(Long id , Professor professor){
        Professor prof = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor Not Found"));

        prof.setProf_id(professor.getProf_id());
        prof.setProf_name(professor.getProf_name());
        prof.setProf_lastName(professor.getProf_lastName());
        prof.setNational_code(professor.getNational_code());

        return professorRepository.save(prof);
    }

    public void deleteProfessor(Long id){
        professorRepository.deleteById(id);
    }

}
