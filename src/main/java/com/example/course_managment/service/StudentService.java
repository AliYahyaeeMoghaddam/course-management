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
    public StudentDTO createStudent(Student student) {

        Student stud = new Student();

        if(student.getClg() == null) {
            College college = collegeRepository.findByName(student.getClg().getName())
                    .orElseThrow(() -> new CollegeNotFoundException("college with ID " + student.getClg().getName() + " not found !"));
            stud.setClg(college);
        }

        if(!student.getStudent_name().isEmpty())
            stud.setStudent_name(student.getStudent_name());
        if(!student.getStudent_lastName().isEmpty())
            stud.setStudent_lastName(student.getStudent_lastName());
        if(student.getNational_code() != 0)
            stud.setNational_code(student.getNational_code());
       if(!student.getAddress().isEmpty())
            stud.setAddress(student.getAddress());

        Student savedStudent = studentRepository.save(stud);
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
        if (studentRepository.findAll().isEmpty())
            throw new StudentNotFoundException("Students not found !");
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<StudentDTO> getStudentsByCollegeName(String college_name) {
        if(collegeRepository.findByName(college_name).isEmpty())
            throw new CollegeNotFoundException("College with name " + college_name + " not found !");
        return studentRepository.findStudentByClgName(college_name)
                .stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public StudentDTO updateStudent(Long id, Student student) {
        Student stud = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found !"));
        if(student.getClg() != null) {
            College clg = collegeRepository.findByName(student.getClg().getName())
                    .orElseThrow(() -> new CollegeNotFoundException("college with ID " + student.getClg().getName() + " not found !"));
            stud.setClg(clg);
        }

        if(!student.getStudent_name().isEmpty())
            stud.setStudent_name(student.getStudent_name());
        if(!student.getStudent_lastName().isEmpty())
            stud.setStudent_lastName(student.getStudent_lastName());
        if(student.getNational_code() != 0)
            stud.setNational_code(student.getNational_code());
        if(!student.getAddress().isEmpty())
            stud.setAddress(student.getAddress());

        Student savedStudent = studentRepository.save(stud);
        return StudentMapper.toDTO(savedStudent);
    }

    @Transactional
    public void deleteStudent(Long id) {
        if(studentRepository.findById(id).isPresent())
            studentRepository.deleteById(id);
        else throw new StudentNotFoundException("Student with ID " + id + " not found !");

    }

    @Transactional
    public StudentDTO CourseRegistrationByStudent (Long stud_id, String course_name) {
        Student stud = studentRepository.findById(stud_id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + stud_id + " not found !"));
        Course crs = courseRepository.findByCourseName(course_name)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + course_name + " not found !"));

        stud.getCourses().add(crs);

        Student savedStudent = studentRepository.save(stud);
        return StudentMapper.toDTO(savedStudent);
    }

    @Transactional
    public StudentDTO DeletingCourseByStudent(Long stud_id, String course_name){
        Student stud = studentRepository.findById(stud_id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + stud_id + " not found !"));
        Course crs = courseRepository.findByCourseName(course_name)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + course_name + " not found !"));

        if(stud.getCourses().contains(crs))
            stud.getCourses().remove(crs);

        Student savedStudent = studentRepository.save(stud);
        return StudentMapper.toDTO(savedStudent);
    }

    @Transactional
    public List<CourseDTO> getStudentCourses(Long student_id) {
        Student stud = studentRepository.findById(student_id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + student_id + " not found !"));

        if(stud.getCourses().isEmpty())
            throw new CourseNotFoundException("Courses not found !");
        return stud.getCourses()
                .stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

}