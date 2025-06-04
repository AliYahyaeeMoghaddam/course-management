package com.example.course_managment.dto;

import java.util.List;

public class CourseDTO {

    private String course_name;
    private int unit;
    private List<Long> studentIds;
    private String collegeName;
    private Long professorId;
    private Long gradeCourseId;


    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public Long getGradeCourseIds() {
        return gradeCourseId;
    }

    public void setGradeCourseIds(Long gradeCourseId) {
        this.gradeCourseId = gradeCourseId;
    }
}
