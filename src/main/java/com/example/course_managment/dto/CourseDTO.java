package com.example.course_managment.dto;

import java.util.List;

public class CourseDTO {

    private String course_name;
    private int unit;
    private List<StudentSimpleDTO> students;
    private CollegeDTO college;
    private ProfessorDTO professor;
    private List<GradeCourseDTO> gradeCourse;

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

    public List<StudentSimpleDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentSimpleDTO> students) {
        this.students = students;
    }

    public CollegeDTO getCollege() {
        return college;
    }

    public void setCollege(CollegeDTO college) {
        this.college = college;
    }

    public ProfessorDTO getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorDTO professor) {
        this.professor = professor;
    }

    public List<GradeCourseDTO> getGradeCourse() {
        return gradeCourse;
    }

    public void setGradeCourse(List<GradeCourseDTO> gradeCourse) {
        this.gradeCourse = gradeCourse;
    }
}
