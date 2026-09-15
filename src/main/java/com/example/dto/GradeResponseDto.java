package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeResponseDto {

    private Long gradeId;

    private Long enrollmentId;

    private Long studentId;

    private String studentName;

    private Long courseId;

    private String courseName;

    private Double marks;

    private String grade;

    private String remarks;
}