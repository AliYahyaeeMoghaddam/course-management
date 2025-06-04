package com.example.course_managment.service;

import com.example.course_managment.dto.AverageCollegeDTO;
import com.example.course_managment.exception.CollegeNotFoundException;
import com.example.course_managment.mapper.AverageCollegeMapper;
import com.example.course_managment.model.AverageCollege;
import com.example.course_managment.model.College;
import com.example.course_managment.model.Student;
import com.example.course_managment.repository.AverageCollegeRepository;
import com.example.course_managment.repository.CollegeRepository;
import com.example.course_managment.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AverageCollegeService {

    private final AverageCollegeRepository averageCollegeRepository;
    private final CollegeRepository collegeRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public AverageCollegeService(AverageCollegeRepository averageCollegeRepository ,
                                 CollegeRepository collegeRepository ,
                                 StudentRepository studentRepository) {
        this.averageCollegeRepository = averageCollegeRepository;
        this.collegeRepository = collegeRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public AverageCollegeDTO setAverageCollege(String college_name) {

        AverageCollege average = new AverageCollege();

        if(college_name.isEmpty())
            throw new CollegeNotFoundException("college is empty");
        average.setCollegeName(college_name);

        College clg = collegeRepository.findByName(college_name)
                .orElseThrow(() -> new CollegeNotFoundException("college not found"));

        List<Student> students = studentRepository.findAll();

        Double ave = 0.0;
        Double sum = 0.0;
        int count = 0;
        for(Student student : students) {
            if(student.getClg().getName().equals(clg.getName())){
                sum += student.getAverageStudent().getAverage();
                count++;
            }
        }
        ave = sum / count;
        average.setAverage(ave);

        AverageCollege averageCollegeSaved = averageCollegeRepository.save(average);
        return AverageCollegeMapper.toDTO(averageCollegeSaved);
    }

}