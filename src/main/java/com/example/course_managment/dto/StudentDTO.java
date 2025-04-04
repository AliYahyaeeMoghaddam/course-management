package com.example.course_managment.dto;

import java.util.List;

public class StudentDTO {

    private Long student_id;
    private String student_name;
    private String student_lastName;
    private List<Long> courseIds;
    private String clgName;
    private List<Long> professorIds;
    private List<Long> gradeCourseIds;

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_lastName() {
        return student_lastName;
    }

    public void setStudent_lastName(String student_lastName) {
        this.student_lastName = student_lastName;
    }

    public List<Long> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }

    public String getClgName() {
        return clgName;
    }

    public void setClgName(String clgName) {
        this.clgName = clgName;
    }

    public List<Long> getProfessorIds() {
        return professorIds;
    }

    public void setProfessorIds(List<Long> professorIds) {
        this.professorIds = professorIds;
    }

    public List<Long> getGradeCourseIds() {
        return gradeCourseIds;
    }

    public void setGradeCourseIds(List<Long> gradeCourseIds) {
        this.gradeCourseIds = gradeCourseIds;
    }
}
