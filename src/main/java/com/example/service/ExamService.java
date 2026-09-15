package com.example.service;

import java.util.List;

import com.example.dto.ExamRequestDto;
import com.example.dto.ExamResponseDto;

public interface ExamService {

    ExamResponseDto createExam(ExamRequestDto requestDto);

    List<ExamResponseDto> getAllExams();

    ExamResponseDto getExamById(Long id);

    ExamResponseDto updateExam(Long id, ExamRequestDto requestDto);

    void deleteExam(Long id);
}