package com.example.course_managment.repository;

import com.example.course_managment.model.GradeCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<GradeCourse , Long> {
    Optional<GradeCourse> findByStudentIdAndCourseId(Long studentId, Long courseId);
}
