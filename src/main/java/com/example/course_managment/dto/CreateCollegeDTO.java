package com.example.course_managment.dto;

public class CreateCollegeDTO {

    private String name;
    private Long prof_manager;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProf_manager() {
        return prof_manager;
    }

    public void setProf_manager(Long prof_manager) {
        this.prof_manager = prof_manager;
    }
}
