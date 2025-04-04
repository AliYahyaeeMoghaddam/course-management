package com.example.course_managment.dto;

import java.util.List;

public class ProfessorDTO {

    private Long prof_id;
    private String prof_name;
    private String prof_lastName;
    private CollegeDTO manager;
    private CollegeDTO college;
    private List<StudentSimpleDTO> students;
    private List<CourseDTO> courses;

    public ProfessorDTO() {}
    public ProfessorDTO(Long prof_id, String prof_name, String prof_lastName,
                        CollegeDTO manager, CollegeDTO college , List<StudentSimpleDTO> students,
                        List<CourseDTO> courses) {
        this.setProf_id(prof_id);
        this.setProf_name(prof_name);
        this.setProf_lastName(prof_lastName);
        this.setManager(manager);
        this.setCollege(college);
        this.setStudents(students);
        this.setCourses(courses);
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

    public CollegeDTO getManager() {
        return manager;
    }

    public void setManager(CollegeDTO manager) {
        this.manager = manager;
    }

    public CollegeDTO getCollege() {
        return college;
    }

    public void setCollege(CollegeDTO college) {
        this.college = college;
    }

    public List<StudentSimpleDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentSimpleDTO> students) {
        this.students = students;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }
}
