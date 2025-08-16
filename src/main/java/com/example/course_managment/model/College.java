package com.example.course_managment.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clg")
public class College {

    @Id
    @Size(min = 5, max = 48)
    @Pattern(regexp = "^[a-zA-Z0-9آ-ی]+$")
    private String name;

    @OneToOne
    @JoinColumn(name = "dean_id",nullable = true)
    private Professor clg_manager;

    @OneToMany(mappedBy = "clg" , cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "college" , cascade = CascadeType.ALL)
    @Column(nullable = false)
    private List<Course> courses = new ArrayList<>();


    @OneToMany(mappedBy = "college" , cascade = CascadeType.ALL)
    private List<Professor> professors = new ArrayList<>();

    public College() {}

    public College(String name) {
        this.name = name;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }
}
