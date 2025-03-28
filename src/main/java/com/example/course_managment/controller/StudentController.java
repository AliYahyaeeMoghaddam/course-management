package com.example.course_managment.controller;

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
    public ResponseEntity<Student> createStudent(@RequestParam String firstName, @RequestParam String lastName,
                                                 @RequestParam Long national_code, @RequestParam String address,
                                                 @RequestParam Long college_id) {
        Student stud = studentService.createStudent(firstName, lastName, national_code, address, college_id);
        return ResponseEntity.ok(stud);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student stud = studentService.getStudentById(id);
        return ResponseEntity.ok(stud);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> studs = studentService.getAllStudents();
        return ResponseEntity.ok(studs);
    }

    @GetMapping("/{college_id}")
    public ResponseEntity<List<Student>> getStudentsByCollegeId(@PathVariable Long college_id) {
        List<Student> studs = studentService.getStudentsByCollegeId(college_id);
        return ResponseEntity.ok(studs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id , @RequestParam String firstName,
                                                 @RequestParam String lastName,
                                                 @RequestParam Long national_code, @RequestParam String address,
                                                 @RequestParam Long college_id) {
        Student stud = studentService.updateStudent(id, firstName, lastName, national_code, address, college_id);
        return ResponseEntity.ok(stud);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @PostMapping("/{stud_id}/courses/{crs_id}")
    public ResponseEntity<String> CourseRegistrationByStudent(@PathVariable Long stud_id, @PathVariable Long crs_id){
        studentService.CourseRegistrationByStudent(stud_id, crs_id);
        return ResponseEntity.ok("Course registration successful");
    }

    @DeleteMapping("/{stud_id}/courses/{course_id}")
    public ResponseEntity<String> DeletingCourseByStudent(@PathVariable Long stud_id, @PathVariable Long course_id){
        studentService.DeletingCourseByStudent(stud_id, course_id);
        return ResponseEntity.ok("Deleting course successful");
    }

    @GetMapping("/{stud_id}/courses")
    public ResponseEntity<List<Course>> getStudentCourses(@PathVariable Long stud_id){
        List<Course> courses = studentService.getStudentCourses(stud_id);
        return ResponseEntity.ok(courses);
    }

}
