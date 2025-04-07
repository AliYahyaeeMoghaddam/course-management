package com.example.course_managment.repository;

import com.example.course_managment.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository <Course, Long> {
    List<Course> findByCollegeName(String college_name);

    List<Course> findByProfessorId(Long professorId);

    Optional<Course> findByCourseName(String course_name);

    void deleteByCourseName(String name);
}
