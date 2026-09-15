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
import com.example.dto.EnrollmentRequestDto;
import com.example.dto.EnrollmentResponseDto;
import com.example.dto.StudentResponseDto;
import com.example.service.EnrollmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<EnrollmentResponseDto>> createEnrollment(
            @Valid @RequestBody EnrollmentRequestDto requestDto) {

        EnrollmentResponseDto enrollment =
                enrollmentService.createEnrollment(requestDto);

        ApiResponse<EnrollmentResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Enrollment created successfully",
                        enrollment,
                        LocalDateTime.now()
                );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EnrollmentResponseDto>>> getAllEnrollments() {

        List<EnrollmentResponseDto> enrollments =
                enrollmentService.getAllEnrollments();

        ApiResponse<List<EnrollmentResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Enrollments fetched successfully",
                        enrollments,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EnrollmentResponseDto>> getEnrollmentById(
            @PathVariable Long id) {

        EnrollmentResponseDto enrollment =
                enrollmentService.getEnrollmentById(id);

        ApiResponse<EnrollmentResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Enrollment fetched successfully",
                        enrollment,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{courseId}/students")
    public ResponseEntity<ApiResponse<List<StudentResponseDto>>> getStudentsByCourse(
            @PathVariable Long courseId) {

        List<StudentResponseDto> students =
                enrollmentService.getStudentsByCourse(courseId);

        ApiResponse<List<StudentResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Students enrolled in course fetched successfully",
                        students,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EnrollmentResponseDto>> updateEnrollment(
            @PathVariable Long id,
            @Valid @RequestBody EnrollmentRequestDto requestDto) {

        EnrollmentResponseDto enrollment =
                enrollmentService.updateEnrollment(id, requestDto);

        ApiResponse<EnrollmentResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Enrollment updated successfully",
                        enrollment,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(
            @PathVariable Long id) {

        enrollmentService.deleteEnrollment(id);

        return ResponseEntity.noContent().build();
    }
}