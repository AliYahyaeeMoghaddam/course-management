package com.example.course_managment.dto;

public class CourseSimpleDTO {

    private String course_name;
    private int unit;

    public CourseSimpleDTO() {}
    public CourseSimpleDTO(String course_name, int unit) {
        this.setCourse_name(course_name);
        this.setUnit(unit);
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
}
