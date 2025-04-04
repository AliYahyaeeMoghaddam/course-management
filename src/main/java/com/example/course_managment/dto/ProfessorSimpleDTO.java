package com.example.course_managment.dto;

public class ProfessorSimpleDTO {

    private Long prof_id;
    private String prof_name;
    private String prof_lastName;

    public ProfessorSimpleDTO() {}
    public ProfessorSimpleDTO(Long prof_id, String prof_name, String prof_lastName) {
        this.setProf_id(prof_id);
        this.setProf_name(prof_name);
        this.setProf_lastName(prof_lastName);
    }

    public Long getProf_id() {
        return prof_id;
    }

    public void setProf_id(Long prof_id) {
        this.prof_id = prof_id;
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
}
