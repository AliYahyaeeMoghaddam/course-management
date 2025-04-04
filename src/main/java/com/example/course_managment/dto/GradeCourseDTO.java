package com.example.course_managment.dto;

public class GradeCourseDTO {

    private Long id;
    private Long studentId;
    private Long courseId;
    private Long garde;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGarde() {
        return garde;
    }

    public void setGarde(Long garde) {
        this.garde = garde;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
