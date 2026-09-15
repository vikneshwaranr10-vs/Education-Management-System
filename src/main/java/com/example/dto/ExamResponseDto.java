package com.example.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponseDto {

    private Long examId;

    private String examName;

    private LocalDate examDate;

    private Integer totalMarks;

    private Long courseId;

    private String courseName;
}