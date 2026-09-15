package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponseDto {

    private Long teacherId;

    private String teacherName;

    private String email;

    private String phone;

    private String specialization;
}