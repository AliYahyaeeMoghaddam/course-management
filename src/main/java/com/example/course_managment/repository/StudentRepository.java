package com.example.course_managment.repository;

import com.example.course_managment.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {
    Collection<Student> findStudentByClgName(String collegeName);
}
