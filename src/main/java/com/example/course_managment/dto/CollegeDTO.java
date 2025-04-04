package com.example.course_managment.dto;

import com.example.course_managment.model.Professor;

import java.util.List;

public class CollegeDTO {

    private String name;
    private ProfessorSimpleDTO clg_manager;
    private List<Long> studentId;
    private List<String> courseName;
    private List<Long> professorId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfessorSimpleDTO getClg_manager() {
        return clg_manager;
    }

    public void setClg_manager(ProfessorSimpleDTO clg_manager) {
        this.clg_manager = clg_manager;
    }

    public List<Long> getStudents() {
        return studentId;
    }

    public void setStudents(List<Long> studentId) {
        this.studentId = studentId;
    }

    public List<String> getCourses() {
        return courseName;
    }

    public void setCourses(List<String> courseName) {
        this.courseName = courseName;
    }

    public List<Long> getProfessors() {
        return professorId;
    }

    public void setProfessors(List<Long> professorId) {
        this.professorId = professorId;
    }
}
