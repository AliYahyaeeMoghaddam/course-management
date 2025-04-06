package com.example.course_managment.dto;

public class CreateCollegeDTO {

    private String name;
    private Long prof_managerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProf_managerId() {
        return prof_managerId;
    }

    public void setProf_managerId(Long prof_managerId) {
        this.prof_managerId = prof_managerId;
    }
}
