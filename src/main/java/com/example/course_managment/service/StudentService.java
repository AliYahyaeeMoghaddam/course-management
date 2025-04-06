package com.example.course_managment.service;

import com.example.course_managment.dto.CourseDTO;
import com.example.course_managment.dto.StudentDTO;
import com.example.course_managment.exception.CollegeNotFoundException;
import com.example.course_managment.exception.CourseNotFoundException;
import com.example.course_managment.exception.StudentNotFoundException;
import com.example.course_managment.mapper.CourseMapper;
import com.example.course_managment.mapper.StudentMapper;
import com.example.course_managment.model.College;
import com.example.course_managment.model.Course;
import com.example.course_managment.model.Student;
import com.example.course_managment.repository.CollegeRepository;
import com.example.course_managment.repository.CourseRepository;
import com.example.course_managment.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CollegeRepository collegeRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentService (StudentRepository studentRepository, CollegeRepository collegeRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.collegeRepository = collegeRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public StudentDTO createStudent(String firstName, String lastName,
                                    Long national_code, String address, Long college_id) {
        College college = collegeRepository.findById(college_id)
                .orElseThrow(() -> new CollegeNotFoundException("college with ID" + college_id + "not found !"));

        Student student = new Student();
        student.setStudent_name(firstName);
        student.setStudent_lastName(lastName);
        student.setNational_code(national_code);
        student.setAddress(address);
        student.setClg(college);

        Student savedStudent = studentRepository.save(student);
        return StudentMapper.toDTO(savedStudent);
    }

    @Transactional
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found !"));
        return StudentMapper.toDTO(student);
    }

    @Transactional
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<StudentDTO> getStudentsByCollegeId(Long college_id) {
        return studentRepository.findByCollegeId(college_id)
                .stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public StudentDTO updateStudent(Long id , String firstName, String lastName,
                                 Long national_code, String address, Long college_id) {
        Student stud = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found !"));
        College clg = collegeRepository.findById(college_id)
                .orElseThrow(() -> new CollegeNotFoundException("college with ID " + id + " not found !"));

        stud.setStudent_name(firstName);
        stud.setStudent_lastName(lastName);
        stud.setNational_code(national_code);
        stud.setAddress(address);
        stud.setClg(clg);

        Student savedStudent = studentRepository.save(stud);
        return StudentMapper.toDTO(savedStudent);
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public StudentDTO CourseRegistrationByStudent (Long stud_id, Long course_id) {
        Student stud = studentRepository.findById(stud_id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + stud_id + " not found !"));
        Course crs = courseRepository.findById(course_id)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + course_id + " not found !"));

        stud.getCourses().add(crs);

        Student savedStudent = studentRepository.save(stud);
        return StudentMapper.toDTO(savedStudent);
    }

    @Transactional
    public StudentDTO DeletingCourseByStudent(Long stud_id, Long course_id){
        Student stud = studentRepository.findById(stud_id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + stud_id + " not found !"));
        Course crs = courseRepository.findById(course_id)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + course_id + " not found !"));

        stud.getCourses().remove(crs);

        Student savedStudent = studentRepository.save(stud);
        return StudentMapper.toDTO(savedStudent);
    }

    @Transactional
    public List<CourseDTO> getStudentCourses(Long student_id) {
        Student stud = studentRepository.findById(student_id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + student_id + " not found !"));

        return stud.getCourses()
                .stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

}
