package com.example.service;

import java.util.List;

import com.example.dto.TeacherRequestDto;
import com.example.dto.TeacherResponseDto;

public interface TeacherService {

    TeacherResponseDto createTeacher(TeacherRequestDto requestDto);

    List<TeacherResponseDto> getAllTeachers();

    TeacherResponseDto getTeacherById(Long id);

    TeacherResponseDto updateTeacher(Long id, TeacherRequestDto requestDto);

    void deleteTeacher(Long id);
}