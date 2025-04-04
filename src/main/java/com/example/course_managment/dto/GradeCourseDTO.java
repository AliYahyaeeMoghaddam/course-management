package com.example.course_managment.dto;

public class GradeCourseDTO {

    private Long id;
    private StudentDTO student;
    private CourseDTO course;
    private Long garde;

    public GradeCourseDTO() {}
    public GradeCourseDTO(Long id, StudentDTO student, CourseDTO course, Long garde) {
        this.setId(id);
        this.setStudent(student);
        this.setCourse(course);
        this.setGarde(garde);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public Long getGarde() {
        return garde;
    }

    public void setGarde(Long garde) {
        this.garde = garde;
    }
}
