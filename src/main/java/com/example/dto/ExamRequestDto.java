package com.example.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamRequestDto {

    @NotBlank(message = "Exam name is required")
    private String examName;

    @NotNull(message = "Exam date is required")
    private LocalDate examDate;

    @NotNull(message = "Total marks is required")
    @Min(value = 1, message = "Total marks must be at least 1")
    @Max(value = 100, message = "Total marks cannot exceed 100")
    private Integer totalMarks;

    @NotNull(message = "Course ID is required")
    private Long courseId;
}