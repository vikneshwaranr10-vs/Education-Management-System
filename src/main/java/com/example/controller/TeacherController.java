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
import com.example.dto.CourseResponseDto;
import com.example.dto.TeacherRequestDto;
import com.example.dto.TeacherResponseDto;
import com.example.service.CourseService;
import com.example.service.TeacherService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final CourseService courseService;

    // Create Teacher
    @PostMapping
    public ResponseEntity<ApiResponse<TeacherResponseDto>> createTeacher(
            @Valid @RequestBody TeacherRequestDto requestDto) {

        TeacherResponseDto teacher =
                teacherService.createTeacher(requestDto);

        ApiResponse<TeacherResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Teacher created successfully",
                        teacher,
                        LocalDateTime.now()
                );

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    // Get All Teachers
    @GetMapping
    public ResponseEntity<ApiResponse<List<TeacherResponseDto>>> getAllTeachers() {

        List<TeacherResponseDto> teachers =
                teacherService.getAllTeachers();

        ApiResponse<List<TeacherResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Teachers fetched successfully",
                        teachers,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    // Get Teacher By ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TeacherResponseDto>> getTeacherById(
            @PathVariable Long id) {

        TeacherResponseDto teacher =
                teacherService.getTeacherById(id);

        ApiResponse<TeacherResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Teacher fetched successfully",
                        teacher,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    // Get Courses By Teacher
    @GetMapping("/{teacherId}/courses")
    public ResponseEntity<ApiResponse<List<CourseResponseDto>>> getCoursesByTeacher(
            @PathVariable Long teacherId) {

        List<CourseResponseDto> courses =
                courseService.getCoursesByTeacher(teacherId);

        ApiResponse<List<CourseResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Teacher courses fetched successfully",
                        courses,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    // Update Teacher
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TeacherResponseDto>> updateTeacher(
            @PathVariable Long id,
            @Valid @RequestBody TeacherRequestDto requestDto) {

        TeacherResponseDto teacher =
                teacherService.updateTeacher(id, requestDto);

        ApiResponse<TeacherResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Teacher updated successfully",
                        teacher,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    // Delete Teacher
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(
            @PathVariable Long id) {

        teacherService.deleteTeacher(id);

        return ResponseEntity.noContent().build();
    }
}