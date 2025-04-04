package com.example.course_managment.dto;

import java.util.List;

public class StudentDTO {

    private Long student_id;
    private String student_name;
    private String student_lastName;
    private List<CourseSimpleDTO> courses;
    private CollegeDTO  clg;
    private List<ProfessorSimpleDTO> professors;
    private List<GradeCourseDTO> gradeCourses;

    public StudentDTO() {}
    public StudentDTO(Long student_id, String student_name,
                      String student_lastName, List<CourseSimpleDTO> courses,
                      CollegeDTO clg, List<ProfessorSimpleDTO> professors ,
                      List<GradeCourseDTO> gradeCourses) {
        this.setStudent_id(student_id);
        this.setStudent_name(student_name);
        this.setStudent_lastName(student_lastName);
        this.setCourses(courses);
        this.setClg(clg);
        this.setProfessors(professors);
        this.setGradeCourses(gradeCourses);
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

    public List<CourseSimpleDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseSimpleDTO> courses) {
        this.courses = courses;
    }

    public CollegeDTO getClg() {
        return clg;
    }

    public void setClg(CollegeDTO clg) {
        this.clg = clg;
    }

    public List<ProfessorSimpleDTO> getProfessors() {
        return professors;
    }

    public void setProfessors(List<ProfessorSimpleDTO> professors) {
        this.professors = professors;
    }

    public List<GradeCourseDTO> getGradeCourses() {
        return gradeCourses;
    }

    public void setGradeCourses(List<GradeCourseDTO> gradeCourses) {
        this.gradeCourses = gradeCourses;
    }
}
