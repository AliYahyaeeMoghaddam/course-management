package com.example.course_managment.service;

import com.example.course_managment.exception.CollegeNotFoundException;
import com.example.course_managment.exception.CourseNotFoundException;
import com.example.course_managment.exception.StudentNotFoundException;
import com.example.course_managment.model.College;
import com.example.course_managment.model.Course;
import com.example.course_managment.model.Student;
import com.example.course_managment.repository.CollegeRepository;
import com.example.course_managment.repository.CourseRepository;
import com.example.course_managment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Student createStudent(String firstName, String lastName,
                                 Long national_code, String address, Long college_id) {
        College college = collegeRepository.findById(college_id)
                .orElseThrow(() -> new CollegeNotFoundException("college with ID" + college_id + "not found !"));

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
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found !"));
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
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found !"));
        College clg = collegeRepository.findById(college_id)
                .orElseThrow(() -> new CollegeNotFoundException("college with ID " + id + " not found !"));

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

    public Student CourseRegistrationByStudent (Long stud_id, Long course_id) {
        Student stud = studentRepository.findById(stud_id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + stud_id + " not found !"));
        Course crs = courseRepository.findById(course_id)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + course_id + " not found !"));

        stud.getCourses().add(crs);

        return studentRepository.save(stud);
    }

    public Student DeletingCourseByStudent(Long stud_id, Long course_id){
        Student stud = studentRepository.findById(stud_id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + stud_id + " not found !"));
        Course crs = courseRepository.findById(course_id)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + course_id + " not found !"));

        stud.getCourses().remove(crs);

        return studentRepository.save(stud);
    }

    public List<Course> getStudentCourses(Long student_id) {
        Student stud = studentRepository.findById(student_id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + student_id + " not found !"));

        return stud.getCourses();
    }

}
