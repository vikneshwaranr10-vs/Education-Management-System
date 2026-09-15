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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ApiResponse;
import com.example.dto.CourseRequestDto;
import com.example.dto.CourseResponseDto;
import com.example.service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponseDto>> createCourse(
            @Valid @RequestBody CourseRequestDto requestDto) {

        CourseResponseDto course = courseService.createCourse(requestDto);

        ApiResponse<CourseResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Course created successfully",
                        course,
                        LocalDateTime.now()
                );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<CourseResponseDto>>> searchCoursesByName(
            @RequestParam String name) {

        List<CourseResponseDto> courses =
                courseService.searchCoursesByName(name);

        ApiResponse<List<CourseResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Courses searched successfully",
                        courses,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search-by-code")
    public ResponseEntity<ApiResponse<List<CourseResponseDto>>> searchCoursesByCode(
            @RequestParam String code) {

        List<CourseResponseDto> courses =
                courseService.searchCoursesByCode(code);

        ApiResponse<List<CourseResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Courses searched by code successfully",
                        courses,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search-by-duration")
    public ResponseEntity<ApiResponse<List<CourseResponseDto>>> searchCoursesByDuration(
            @RequestParam Integer duration) {

        List<CourseResponseDto> courses =
                courseService.searchCoursesByDuration(duration);

        ApiResponse<List<CourseResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Courses searched by duration successfully",
                        courses,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponseDto>>> getAllCourses() {

        List<CourseResponseDto> courses =
                courseService.getAllCourses();

        ApiResponse<List<CourseResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Courses fetched successfully",
                        courses,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponseDto>> getCourseById(
            @PathVariable Long id) {

        CourseResponseDto course =
                courseService.getCourseById(id);

        ApiResponse<CourseResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Course fetched successfully",
                        course,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponseDto>> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequestDto requestDto) {

        CourseResponseDto course =
                courseService.updateCourse(id, requestDto);

        ApiResponse<CourseResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Course updated successfully",
                        course,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable Long id) {

        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }
}