package com.example.course_managment.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;

    @Column(nullable = false , length = 48)
    private String student_name;

    @Column(nullable = false , length = 48)
    private String student_lastName;

    @Column(nullable = false)
    private Long national_code;

    @Column(nullable = true , length = 127)
    private String Address;

    @Column(nullable = false)
    @ManyToMany
    @JoinTable(
            name = "stud_crs" ,
            joinColumns = @JoinColumn(name = "stud_id") ,
            inverseJoinColumns = @JoinColumn(name = "crs_id")
    )
    private List<Course> courses = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "clg_id" , nullable = false)
    private College  clg;

    @ManyToMany(mappedBy = "students")
    private List<Professor> professors = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<GradeCourse> gradeCourses = new ArrayList<>();


    public Student() {}
    public Student(String student_name, String student_lastName, Long national_code, String Address , College clg) {
        this.student_name = student_name;
        this.student_lastName = student_lastName;
        this.national_code = national_code;
        this.Address = Address;
        this.setClg(clg);
    }

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

    public Long getNational_code() {
        return national_code;
    }

    public void setNational_code(Long national_code) {
        this.national_code = national_code;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public College getClg() {
        return clg;
    }

    public void setClg(College clg) {
        this.clg = clg;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    public List<GradeCourse> getGradeCourses() {
        return gradeCourses;
    }

    public void setGradeCourses(List<GradeCourse> gradeCourses) {
        this.gradeCourses = gradeCourses;
    }
}