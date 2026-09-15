package com.example.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponseDto {

    private Long enrollmentId;

    private Long studentId;

    private String studentName;

    private Long courseId;

    private String courseName;

    private LocalDate enrollmentDate;

    private String status;
}