package com.example.course_managment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGeneralException(Exception ex , WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                "internal server error !" ,
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProfessorNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleProfessorNotFoundException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CollegeNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleCollegeNotFoundException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleCourseNotFoundException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleStudentNotFoundException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}

