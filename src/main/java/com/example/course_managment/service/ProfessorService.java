package com.example.course_managment.service;

import com.example.course_managment.dto.ProfessorDTO;
import com.example.course_managment.dto.StudentDTO;
import com.example.course_managment.exception.CollegeNotFoundException;
import com.example.course_managment.exception.ProfessorNotFoundException;
import com.example.course_managment.exception.StudentNotFoundException;
import com.example.course_managment.mapper.ProfessorMapper;
import com.example.course_managment.mapper.StudentMapper;
import com.example.course_managment.model.College;
import com.example.course_managment.model.Professor;
import com.example.course_managment.model.Student;
import com.example.course_managment.repository.CollegeRepository;
import com.example.course_managment.repository.ProfessorRepository;
import com.example.course_managment.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final CollegeRepository collegeRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository,
                            CollegeRepository collegeRepository,
                            StudentRepository studentRepository) {
        this.professorRepository = professorRepository;
        this.collegeRepository = collegeRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public ProfessorDTO createProfessor(Professor professor) {

        Professor prof = new Professor();

        if (professor.getCollege() != null) {
            College clg = collegeRepository.findByName(professor.getCollege().getName())
                    .orElseThrow(() -> new CollegeNotFoundException("college with ID " + professor.getCollege().getName() + " not found !"));
            prof.setCollege(clg);
        }

        if(!professor.getProf_name().isEmpty())
            prof.setProf_name(professor.getProf_name());
        if(!professor.getProf_lastName().isEmpty())
            prof.setProf_lastName(professor.getProf_lastName());
        if(professor.getNational_code() != 0)
            prof.setNational_code(professor.getNational_code());

        Professor savedProfessor = professorRepository.save(prof);
        return ProfessorMapper.toDTO(savedProfessor);
    }

    @Transactional
    public List<ProfessorDTO> getAllProfessors(){
        return professorRepository.findAll()
                .stream()
                .map(ProfessorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProfessorDTO getProfessorById(Long id){
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID " + id + " not found !"));
        return ProfessorMapper.toDTO(professor);
    }

    @Transactional
    public List<ProfessorDTO> getProfessorsByCollegeName(String college_name){
        if(collegeRepository.findByName(college_name).isEmpty())
            throw new CollegeNotFoundException("College with name " + college_name + " not found !");
        return professorRepository.findByCollegeName(college_name)
                .stream()
                .map(ProfessorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProfessorDTO updateProfessor(Long id, Professor professor) {
        Professor prof = professorRepository.findById(id)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID " + id + " not found !"));
        if(professor.getCollege() != null) {
            College clg = collegeRepository.findByName(professor.getCollege().getName())
                    .orElseThrow(() -> new CollegeNotFoundException("college with ID " + professor.getCollege().getName() + " not found !"));
            prof.setCollege(clg);
        }

        if(!professor.getProf_name().isEmpty())
            prof.setProf_name(professor.getProf_name());
        if(!professor.getProf_lastName().isEmpty())
            prof.setProf_lastName(professor.getProf_lastName());
        if(professor.getNational_code() != 0)
            prof.setNational_code(professor.getNational_code());

        Professor savedProfessor = professorRepository.save(prof);
        return ProfessorMapper.toDTO(savedProfessor);
    }

    @Transactional
    public void deleteProfessor(Long id){
        if(professorRepository.findById(id).isPresent())
            professorRepository.deleteById(id);
    }

    @Transactional
    public ProfessorDTO AddingStudentByProfessor (Long prof_id, Long student_id){
        Professor professor = professorRepository.findById(prof_id)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID " + prof_id + " not found !"));
        Student student = studentRepository.findById(student_id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + student_id + " not found !"));

        professor.getStudents().add(student);

        Professor savedProfessor = professorRepository.save(professor);
        return ProfessorMapper.toDTO(savedProfessor);
    }

    @Transactional
    public ProfessorDTO DeleteStudentByProfessor (Long prof_id, Long student_id){
        Professor professor = professorRepository.findById(prof_id)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID " + prof_id + " not found !"));
        Student student = studentRepository.findById(student_id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + student_id + " not found !"));

        if(professor.getStudents().contains(student))
            professor.getStudents().remove(student);

        Professor savedProfessor = professorRepository.save(professor);
        return ProfessorMapper.toDTO(savedProfessor);
    }


    @Transactional
    public List<StudentDTO> getStudentsOfProfessor(Long prof_id){
        Professor professor = professorRepository.findById(prof_id)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor with ID " + prof_id + " not found !"));
        if(professor.getStudents().isEmpty())
            throw new StudentNotFoundException("Students not found !");
        return professor.getStudents()
                .stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

}
