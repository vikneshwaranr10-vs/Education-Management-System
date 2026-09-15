package com.example.service;

import java.util.List;

import com.example.dto.GradeRequestDto;
import com.example.dto.GradeResponseDto;

public interface GradeService {

    GradeResponseDto createGrade(GradeRequestDto requestDto);

    List<GradeResponseDto> getAllGrades();

    GradeResponseDto getGradeById(Long id);

    GradeResponseDto updateGrade(
            Long id,
            GradeRequestDto requestDto);

    void deleteGrade(Long id);

    String calculateGrade(Double marks);
}