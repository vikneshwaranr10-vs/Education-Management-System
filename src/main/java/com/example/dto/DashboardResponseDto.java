package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponseDto {

    private long totalStudents;
    private long totalTeachers;
    private long totalCourses;
    private long totalEnrollments;
    private long totalExams;
    private long totalGrades;
}