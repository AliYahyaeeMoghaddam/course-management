package com.example.course_managment.service;

import com.example.course_managment.dto.AverageProfessorDTO;
import com.example.course_managment.exception.CourseNotFoundException;
import com.example.course_managment.mapper.AverageProfessorMapper;
import com.example.course_managment.model.AverageProfessor;
import com.example.course_managment.model.Course;
import com.example.course_managment.model.Professor;
import com.example.course_managment.model.Student;
import com.example.course_managment.repository.AverageProfessorRepository;
import com.example.course_managment.repository.CourseRepository;
import com.example.course_managment.repository.ProfessorRepository;
import com.example.course_managment.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AverageProfessorService {

    final private AverageProfessorRepository averageProfessorRepository;
    final private ProfessorRepository professorRepository;
    final private CourseRepository courseRepository;
    final private StudentRepository studentRepository;

    @Autowired
    public AverageProfessorService(AverageProfessorRepository averageProfessorRepository,
                                   ProfessorRepository professorRepository,
                                   CourseRepository courseRepository,
                                   StudentRepository studentRepository) {
        this.averageProfessorRepository = averageProfessorRepository;
        this.professorRepository = professorRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public AverageProfessorDTO setAverageProfessor(Long professorId, String courseName) {

        Professor prof = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor Not Found"));
        Course crs = courseRepository.findByCourseName(courseName);
        if (crs == null)
            throw new CourseNotFoundException("Course Not Found");

        List<Student> students = studentRepository.findAll();

        AverageProfessor averageProfessor = new AverageProfessor();

        Double ave = 0.0;
        Double sum = 0.0;
        int count = 0;

        for (Student student : students) {
            if(student.getProfessors().contains(prof) && student.getCourses().contains(crs) &&
                    prof.getCourses().contains(crs) && crs.getProfessor().equals(prof)) {
                sum += student.getAverageStudent().getAverage();
                count++;
            }
        }

        ave = sum / count;
        averageProfessor.setAverage(ave);

        AverageProfessor averageSaved = averageProfessorRepository.save(averageProfessor);
        return AverageProfessorMapper.toDTO(averageSaved);
    }

}
