package com.example.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {

    private Long studentId;

    private String studentName;

    private String email;

    private String phone;

    private String address;

    private LocalDate dateOfBirth;
}