package com.example.course_managment.repository;

import com.example.course_managment.model.AverageProfessor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AverageProfessorRepository extends JpaRepository<AverageProfessor , String> {
}
