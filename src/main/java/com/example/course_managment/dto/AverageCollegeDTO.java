package com.example.course_managment.dto;

import java.util.List;

public class AverageCollegeDTO {

//    private Long id;
    private String collegeName;
//    private List<Long> student_names;
    private Double average_college;


//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

//    public List<Long> getStudent_names() {
//        return student_names;
//    }
//
//    public void setStudent_names(List<Long> student_names) {
//        this.student_names = student_names;
//    }

    public Double getAverage_college() {
        return average_college;
    }

    public void setAverage_college(Double average_college) {
        this.average_college = average_college;
    }
}
