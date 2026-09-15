package com.example.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.EnrollmentRequestDto;
import com.example.dto.EnrollmentResponseDto;
import com.example.dto.StudentResponseDto;
import com.example.entity.Course;
import com.example.entity.Enrollment;
import com.example.entity.Student;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.EnrollmentMapper;
import com.example.mapper.StudentMapper;
import com.example.repository.CourseRepository;
import com.example.repository.EnrollmentRepository;
import com.example.repository.StudentRepository;
import com.example.service.EnrollmentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final StudentMapper studentMapper;

    @Override
    public EnrollmentResponseDto createEnrollment(
            EnrollmentRequestDto requestDto) {

        Enrollment enrollment = enrollmentMapper.toEntity(requestDto);

        Student student = studentRepository
                .findById(requestDto.getStudentId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Student not found with id: "
                        + requestDto.getStudentId()));

        Course course = courseRepository
                .findById(requestDto.getCourseId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Course not found with id: "
                        + requestDto.getCourseId()));

        enrollment.setStudent(student);
        enrollment.setCourse(course);

        Enrollment savedEnrollment =
                enrollmentRepository.save(enrollment);

        return enrollmentMapper.toResponseDto(savedEnrollment);
    }

    @Override
    public List<EnrollmentResponseDto> getAllEnrollments() {

        return enrollmentRepository.findAll()
                .stream()
                .map(enrollmentMapper::toResponseDto)
                .toList();
    }

    @Override
    public EnrollmentResponseDto getEnrollmentById(Long id) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Enrollment not found with id: " + id));

        return enrollmentMapper.toResponseDto(enrollment);
    }

    @Override
    public EnrollmentResponseDto updateEnrollment(
            Long id,
            EnrollmentRequestDto requestDto) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Enrollment not found with id: " + id));

        Student student = studentRepository
                .findById(requestDto.getStudentId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Student not found with id: "
                        + requestDto.getStudentId()));

        Course course = courseRepository
                .findById(requestDto.getCourseId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Course not found with id: "
                        + requestDto.getCourseId()));

        enrollment.setEnrollmentDate(
                requestDto.getEnrollmentDate());

        enrollment.setStatus(
                requestDto.getStatus());

        enrollment.setStudent(student);
        enrollment.setCourse(course);

        Enrollment updatedEnrollment =
                enrollmentRepository.save(enrollment);

        return enrollmentMapper.toResponseDto(updatedEnrollment);
    }

    @Override
    public void deleteEnrollment(Long id) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Enrollment not found with id: " + id));

        enrollmentRepository.delete(enrollment);
    }

    // Student Enrollment History
    @Override
    public List<EnrollmentResponseDto> getStudentEnrollmentHistory(
            Long studentId) {

        log.info(
            "Fetching enrollment history for student id: {}",
            studentId
        );

        // Check whether student exists
        studentRepository.findById(studentId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Student not found with id: " + studentId));

        return enrollmentRepository
                .findByStudentStudentId(studentId)
                .stream()
                .map(enrollmentMapper::toResponseDto)
                .toList();
    }

    // Course-wise Students
    @Override
    public List<StudentResponseDto> getStudentsByCourse(
            Long courseId) {

        log.info(
            "Fetching students for course id: {}",
            courseId
        );

        // Check whether course exists
        courseRepository.findById(courseId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Course not found with id: " + courseId));

        return enrollmentRepository
                .findByCourseCourseId(courseId)
                .stream()
                .map(enrollment ->
                    studentMapper.toResponseDto(
                        enrollment.getStudent()))
                .toList();
    }
}