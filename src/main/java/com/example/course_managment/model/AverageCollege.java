package com.example.course_managment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Average_College")
public class AverageCollege {

    @Id
    @Column(nullable = false)
    private String collegeName;

    private Double average;

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }
}
