package com.example.course_managment.service;

import com.example.course_managment.model.College;
import com.example.course_managment.model.Student;
import com.example.course_managment.repository.CollegeRepository;
import com.example.course_managment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CollegeRepository collegeRepository;

    @Autowired
    public StudentService (StudentRepository studentRepository, CollegeRepository collegeRepository) {
        this.studentRepository = studentRepository;
        this.collegeRepository = collegeRepository;
    }

    public Student createStudent(String firstName, String lastName,
                                 Long national_code, String address, Long college_id) {
        College college = collegeRepository.findById(college_id)
                .orElseThrow(() -> new RuntimeException("College not found"));

        Student student = new Student();
        student.setStudent_name(firstName);
        student.setStudent_lastName(lastName);
        student.setNational_code(national_code);
        student.setAddress(address);
        student.setClg(college);

        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByCollegeId(Long college_id) {
        return studentRepository.findByCollegeId(college_id);
    }

    public Student updateStudent(Long id , String firstName, String lastName,
                                 Long national_code, String address, Long college_id) {
        Student stud = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        College clg = collegeRepository.findById(college_id)
                .orElseThrow(() -> new RuntimeException("College not found"));

        stud.setStudent_name(firstName);
        stud.setStudent_lastName(lastName);
        stud.setNational_code(national_code);
        stud.setAddress(address);
        stud.setClg(clg);

        return studentRepository.save(stud);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}
