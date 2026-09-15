package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.dto.StudentRequestDto;
import com.example.dto.StudentResponseDto;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto requestDto);

    List<StudentResponseDto> getAllStudents();

    StudentResponseDto getStudentById(Long id);

    StudentResponseDto updateStudent(Long id, StudentRequestDto requestDto);

    void deleteStudent(Long id);

    List<StudentResponseDto> searchStudentsByName(String name);

    StudentResponseDto searchStudentByEmail(String email);

    Page<StudentResponseDto> getStudentsWithPagination(
            int page,
            int size,
            String sortBy,
            String direction);
}