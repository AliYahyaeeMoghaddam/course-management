package com.example.course_managment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "grade")
public class GradeCourse {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Student_id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable = false)
    private Double garde;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Double getGarde() {
        return garde;
    }

    public void setGarde(Double garde) {
        this.garde = garde;
    }
}
