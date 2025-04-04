package com.example.course_managment.dto;

import com.example.course_managment.model.Professor;

import java.util.List;

public class CollegeDTO {

    private String name;
    private Professor clg_manager;
    private List<StudentDTO> students;
    private List<CourseDTO> courses;
    private List<ProfessorDTO> professors;

    public CollegeDTO() {}
    public CollegeDTO(String name, Professor clg_manager,
                      List<StudentDTO> students , List<CourseDTO> courses
                      , List<ProfessorDTO> professors) {
        this.setName(name);
        this.setClg_manager(clg_manager);
        this.setStudents(students);
        this.setCourses(courses);
        this.setProfessors(professors);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Professor getClg_manager() {
        return clg_manager;
    }

    public void setClg_manager(Professor clg_manager) {
        this.clg_manager = clg_manager;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }

    public List<ProfessorDTO> getProfessors() {
        return professors;
    }

    public void setProfessors(List<ProfessorDTO> professors) {
        this.professors = professors;
    }
}
