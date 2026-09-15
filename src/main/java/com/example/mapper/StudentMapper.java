package com.example.mapper;

import org.springframework.stereotype.Component;

import com.example.dto.StudentRequestDto;
import com.example.dto.StudentResponseDto;
import com.example.entity.Student;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequestDto dto) {

        Student student = new Student();

        student.setStudentName(dto.getStudentName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setAddress(dto.getAddress());
        student.setDateOfBirth(dto.getDateOfBirth());

        return student;
    }

    public StudentResponseDto toResponseDto(Student student) {

        StudentResponseDto dto = new StudentResponseDto();

        dto.setStudentId(student.getStudentId());
        dto.setStudentName(student.getStudentName());
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone());
        dto.setAddress(student.getAddress());
        dto.setDateOfBirth(student.getDateOfBirth());

        return dto;
    }
}