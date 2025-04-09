package com.example.course_managment.controller;

import com.example.course_managment.dto.CourseDTO;
import com.example.course_managment.dto.StudentDTO;
import com.example.course_managment.model.Course;
import com.example.course_managment.model.Student;
import com.example.course_managment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody Student student) {
        StudentDTO stud = studentService.createStudent(student);
        return ResponseEntity.ok(stud);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO stud = studentService.getStudentById(id);
        return ResponseEntity.ok(stud);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudents() {
        List<StudentDTO> studs = studentService.getAllStudents();
        return ResponseEntity.ok(studs);
    }

    @GetMapping("/{college_name}")
    public ResponseEntity<List<StudentDTO>> getStudentsByCollegeId(@PathVariable String college_name) {
        List<StudentDTO> studs = studentService.getStudentsByCollegeName(college_name);
        return ResponseEntity.ok(studs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id , @RequestBody Student student) {
        StudentDTO stud = studentService.updateStudent(id, student);
        return ResponseEntity.ok(stud);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @PostMapping("/{stud_id}/courses/{crs_name}")
    public ResponseEntity<String> CourseRegistrationByStudent(@PathVariable Long stud_id, @PathVariable String crs_name){
        studentService.CourseRegistrationByStudent(stud_id, crs_name);
        return ResponseEntity.ok("Course registration successful");
    }

    @DeleteMapping("/{stud_id}/courses/{course_name}")
    public ResponseEntity<String> DeletingCourseByStudent(@PathVariable Long stud_id, @PathVariable String course_name){
        studentService.DeletingCourseByStudent(stud_id, course_name);
        return ResponseEntity.ok("Deleting course successful");
    }

    @GetMapping("/{stud_id}/courses")
    public ResponseEntity<List<CourseDTO>> getStudentCourses(@PathVariable Long stud_id){
        List<CourseDTO> courses = studentService.getStudentCourses(stud_id);
        return ResponseEntity.ok(courses);
    }

}
