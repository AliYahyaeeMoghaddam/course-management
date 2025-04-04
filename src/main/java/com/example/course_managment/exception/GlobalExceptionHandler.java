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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGeneralException(Exception ex , WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                "internal server error !" ,
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProfessorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleProfessorNotFoundException(Exception ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails.toString(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CollegeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleCollegeNotFoundException(Exception ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                request.getDescription(false)

        );

        return new ResponseEntity<>(errorDetails.toString(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleCourseNotFoundException(Exception ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails.toString(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleStudentNotFoundException(Exception ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails.toString(), HttpStatus.NOT_FOUND);
    }

}
