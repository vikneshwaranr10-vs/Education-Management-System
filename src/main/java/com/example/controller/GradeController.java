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
import com.example.dto.GradeRequestDto;
import com.example.dto.GradeResponseDto;
import com.example.service.GradeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping
    public ResponseEntity<ApiResponse<GradeResponseDto>> createGrade(
            @Valid @RequestBody GradeRequestDto requestDto) {

        GradeResponseDto grade = gradeService.createGrade(requestDto);

        ApiResponse<GradeResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Grade created successfully",
                        grade,
                        LocalDateTime.now()
                );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GradeResponseDto>>> getAllGrades() {

        List<GradeResponseDto> grades = gradeService.getAllGrades();

        ApiResponse<List<GradeResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Grades fetched successfully",
                        grades,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GradeResponseDto>> getGradeById(
            @PathVariable Long id) {

        GradeResponseDto grade = gradeService.getGradeById(id);

        ApiResponse<GradeResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Grade fetched successfully",
                        grade,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GradeResponseDto>> updateGrade(
            @PathVariable Long id,
            @Valid @RequestBody GradeRequestDto requestDto) {

        GradeResponseDto grade =
                gradeService.updateGrade(id, requestDto);

        ApiResponse<GradeResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Grade updated successfully",
                        grade,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(
            @PathVariable Long id) {

        gradeService.deleteGrade(id);

        return ResponseEntity.noContent().build();
    }
}