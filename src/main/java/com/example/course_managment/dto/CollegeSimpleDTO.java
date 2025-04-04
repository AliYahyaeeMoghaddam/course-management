package com.example.course_managment.dto;

public class CollegeSimpleDTO {

    private String name;

    public CollegeSimpleDTO(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
