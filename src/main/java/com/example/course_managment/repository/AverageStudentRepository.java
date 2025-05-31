package com.example.course_managment.repository;

import com.example.course_managment.model.AverageStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AverageStudentRepository  extends JpaRepository<AverageStudent, Long> {
    Optional<AverageStudent> findByStudentId(Long id);
}
