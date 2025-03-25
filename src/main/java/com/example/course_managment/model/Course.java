package com.example.course_managment.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "crss")
public class Course {

    @Id
    private String course_name;

    @Column(nullable = false)
    private int unit;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "crs_id" , nullable = false)
    private College college;

    @ManyToOne
    @JoinColumn(name = "crs_id")
    private Professor professor;

    public Course() {}
    public Course(String course_name, int unit, College college , Professor professor) {
        this.course_name = course_name;
        this.unit = unit;
        this.setCollege(college);
        this.setProfessor(professor);
    }

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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
