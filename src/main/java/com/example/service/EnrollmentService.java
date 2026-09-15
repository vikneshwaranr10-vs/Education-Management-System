package com.example.service;

import java.util.List;
import com.example.dto.StudentResponseDto;
import com.example.dto.EnrollmentRequestDto;
import com.example.dto.EnrollmentResponseDto;

public interface EnrollmentService {

    EnrollmentResponseDto createEnrollment(EnrollmentRequestDto requestDto);

    List<EnrollmentResponseDto> getAllEnrollments();

    EnrollmentResponseDto getEnrollmentById(Long id);

    EnrollmentResponseDto updateEnrollment(Long id,
            EnrollmentRequestDto requestDto);

    void deleteEnrollment(Long id);
    
    List<EnrollmentResponseDto> getStudentEnrollmentHistory(Long studentId);
    
    List<StudentResponseDto> getStudentsByCourse(Long courseId);
}