package com.example.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ApiResponse;
import com.example.dto.ExamRequestDto;
import com.example.dto.ExamResponseDto;
import com.example.service.ExamService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping
    public ResponseEntity<ApiResponse<ExamResponseDto>> createExam(
            @Valid @RequestBody ExamRequestDto requestDto) {

        ExamResponseDto exam = examService.createExam(requestDto);

        ApiResponse<ExamResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Exam created successfully",
                        exam,
                        LocalDateTime.now()
                );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ExamResponseDto>>> getAllExams() {

        List<ExamResponseDto> exams = examService.getAllExams();

        ApiResponse<List<ExamResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Exams fetched successfully",
                        exams,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExamResponseDto>> getExamById(
            @PathVariable Long id) {

        ExamResponseDto exam = examService.getExamById(id);

        ApiResponse<ExamResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Exam fetched successfully",
                        exam,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ExamResponseDto>> updateExam(
            @PathVariable Long id,
            @Valid @RequestBody ExamRequestDto requestDto) {

        ExamResponseDto exam =
                examService.updateExam(id, requestDto);

        ApiResponse<ExamResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Exam updated successfully",
                        exam,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(
            @PathVariable Long id) {

        examService.deleteExam(id);

        return ResponseEntity.noContent().build();
    }
}