package com.example.course_managment.repository;

import com.example.course_managment.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository <Professor, Long> {
    List<Professor> findByCollegeId(Long collegeId);
}
