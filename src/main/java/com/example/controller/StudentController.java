package com.example.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ApiResponse;
import com.example.dto.EnrollmentResponseDto;
import com.example.dto.StudentRequestDto;
import com.example.dto.StudentResponseDto;
import com.example.service.EnrollmentService;
import com.example.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final EnrollmentService enrollmentService;

    // Create Student
    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponseDto>> createStudent(
            @Valid @RequestBody StudentRequestDto requestDto) {

        StudentResponseDto student =
                studentService.createStudent(requestDto);

        ApiResponse<StudentResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Student created successfully",
                        student,
                        LocalDateTime.now()
                );

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    // Get All Students
    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponseDto>>> getAllStudents() {

        List<StudentResponseDto> students =
                studentService.getAllStudents();

        ApiResponse<List<StudentResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Students fetched successfully",
                        students,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    // Get Student By ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudentById(
            @PathVariable Long id) {

        StudentResponseDto student =
                studentService.getStudentById(id);

        ApiResponse<StudentResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Student fetched successfully",
                        student,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    // Student Enrollment History
    @GetMapping("/{studentId}/enrollments")
    public ResponseEntity<ApiResponse<List<EnrollmentResponseDto>>>
            getStudentEnrollmentHistory(
                    @PathVariable Long studentId) {

        List<EnrollmentResponseDto> enrollments =
                enrollmentService.getStudentEnrollmentHistory(studentId);

        ApiResponse<List<EnrollmentResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Student enrollment history fetched successfully",
                        enrollments,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    // Search Students By Name
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<StudentResponseDto>>>
            searchStudentsByName(
                    @RequestParam String name) {

        List<StudentResponseDto> students =
                studentService.searchStudentsByName(name);

        ApiResponse<List<StudentResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Students searched successfully",
                        students,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    // Search Student By Email
    @GetMapping("/search-by-email")
    public ResponseEntity<ApiResponse<StudentResponseDto>>
            searchStudentByEmail(
                    @RequestParam String email) {

        StudentResponseDto student =
                studentService.searchStudentByEmail(email);

        ApiResponse<StudentResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Student found successfully",
                        student,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    // Pagination and Sorting
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<StudentResponseDto>>>
            getStudentsWithPagination(
                    @RequestParam(defaultValue = "0") int page,
                    @RequestParam(defaultValue = "5") int size,
                    @RequestParam(defaultValue = "studentId") String sortBy,
                    @RequestParam(defaultValue = "asc") String direction) {

        Page<StudentResponseDto> students =
                studentService.getStudentsWithPagination(
                        page,
                        size,
                        sortBy,
                        direction
                );

        ApiResponse<Page<StudentResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Students fetched with pagination successfully",
                        students,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    // Update Student
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDto requestDto) {

        StudentResponseDto student =
                studentService.updateStudent(id, requestDto);

        ApiResponse<StudentResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Student updated successfully",
                        student,
                        LocalDateTime.now()
                );

        return ResponseEntity.ok(response);
    }

    // Delete Student
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);
    }
}