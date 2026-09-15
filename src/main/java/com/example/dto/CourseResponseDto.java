package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {

    private Long courseId;

    private String courseName;

    private String courseCode;

    private String description;

    private Integer duration;

    private Long teacherId;

    private String teacherName;
}