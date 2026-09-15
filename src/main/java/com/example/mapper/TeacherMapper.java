package com.example.mapper;

import org.springframework.stereotype.Component;

import com.example.dto.TeacherRequestDto;
import com.example.dto.TeacherResponseDto;
import com.example.entity.Teacher;

@Component
public class TeacherMapper {

    public Teacher toEntity(TeacherRequestDto dto) {

        Teacher teacher = new Teacher();

        teacher.setTeacherName(dto.getTeacherName());
        teacher.setEmail(dto.getEmail());
        teacher.setPhone(dto.getPhone());
        teacher.setSpecialization(dto.getSpecialization());

        return teacher;
    }

    public TeacherResponseDto toResponseDto(Teacher teacher) {

        TeacherResponseDto dto = new TeacherResponseDto();

        dto.setTeacherId(teacher.getTeacherId());
        dto.setTeacherName(teacher.getTeacherName());
        dto.setEmail(teacher.getEmail());
        dto.setPhone(teacher.getPhone());
        dto.setSpecialization(teacher.getSpecialization());

        return dto;
    }
}