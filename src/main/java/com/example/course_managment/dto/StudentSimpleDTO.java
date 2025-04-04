package com.example.course_managment.dto;

public class StudentSimpleDTO {

    private Long student_id;
    private String student_name;
    private String student_lastName;

    public StudentSimpleDTO() {}
    public StudentSimpleDTO(Long student_id, String student_name, String student_lastName) {
        this.setStudent_id(student_id);
        this.setStudent_name(student_name);
        this.setStudent_lastName(student_lastName);
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
}
