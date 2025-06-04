package com.example.course_managment.dto;

public class GradeCourseDTO {

    private Long id;
    private Long studentId;
    private String courseName;
    private Double garde;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGarde() {
        return garde;
    }

    public void setGarde(Double garde) {
        this.garde = garde;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
