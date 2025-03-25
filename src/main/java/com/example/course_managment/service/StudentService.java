package com.example.course_managment.service;

import com.example.course_managment.model.Student;
import com.example.course_managment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService (StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id , Student student) {
        Student stud = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        stud.setStudent_id(student.getStudent_id());
        stud.setStudent_name(student.getStudent_name());
        stud.setStudent_lastName(student.getStudent_lastName());
        stud.setNational_code(student.getNational_code());
        stud.setAddress(student.getAddress());

        return studentRepository.save(stud);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}
