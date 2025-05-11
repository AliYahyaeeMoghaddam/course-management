package com.example.course_managment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profs")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Zآ-ی]+$")
    private String prof_name;

    @Column(name = "prof_lastName",nullable = false , length = 48)
    @Pattern(regexp = "^[a-zA-Zآ-ی]+$")
    private String prof_lastName;

    @Column(nullable = false , unique = true)
    private Long national_code;

    @OneToOne(mappedBy = "clg_manager")
    private College manager;

    @ManyToOne
    @JoinColumn(name = "college_name" ,nullable = false)
    private College college;

    @ManyToMany
    @JoinTable(
            name = "prof_stud" ,
            joinColumns = @JoinColumn (name = "prof" ) ,
            inverseJoinColumns = @JoinColumn(name = "stud")
    )
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "professor")
    private List<Course> courses = new ArrayList<>();

    public Professor() {}

    public Professor(String prof_name, String prof_lastName, Long national_code, College college) {
        this.prof_name = prof_name;
        this.prof_lastName = prof_lastName;
        this.national_code = national_code;
        this.setCollege(college);
    }


    public Long getProf_id() {
        return id;
    }

    public void setProf_id(Long id) {
        this.id = id;
    }

    public String getProf_name() {
        return prof_name;
    }

    public void setProf_name(String prof_name) {
        this.prof_name = prof_name;
    }

    public String getProf_lastName() {
        return prof_lastName;
    }

    public void setProf_lastName(String prof_lastName) {
        this.prof_lastName = prof_lastName;
    }

    public Long getNational_code() {
        return national_code;
    }

    public void setNational_code(Long national_code) {
        this.national_code = national_code;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public College getManager() {
        return manager;
    }

    public void setManager(College manager) {
        this.manager = manager;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
