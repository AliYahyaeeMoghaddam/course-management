package com.example.course_managment.dto;

import java.util.List;

public class ProfessorDTO {

    private Long prof_id;
    private String prof_name;
    private String prof_lastName;
    //private Long manager;//college manager
    private String college;
    private List<Long> studentIds;
    private List<String> courseNames;

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

//    public Long getManager() {
//        return manager;
//    }
//
//    public void setManager(Long manager) {
//        this.manager = manager;
//    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }

    public List<String> getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(List<String> courseNames) {
        this.courseNames = courseNames;
    }
}
