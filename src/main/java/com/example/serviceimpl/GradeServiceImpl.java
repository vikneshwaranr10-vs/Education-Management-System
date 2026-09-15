package com.example.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.GradeRequestDto;
import com.example.dto.GradeResponseDto;
import com.example.entity.Enrollment;
import com.example.entity.Grade;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.GradeMapper;
import com.example.repository.EnrollmentRepository;
import com.example.repository.GradeRepository;
import com.example.service.GradeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final GradeMapper gradeMapper;

    @Override
    public GradeResponseDto createGrade(GradeRequestDto requestDto) {

        log.info("Creating grade for enrollment id: {}",
                requestDto.getEnrollmentId());

        Grade grade = gradeMapper.toEntity(requestDto);

        Enrollment enrollment = enrollmentRepository
                .findById(requestDto.getEnrollmentId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Enrollment not found with id: "
                        + requestDto.getEnrollmentId()));

        // Automatically calculate grade from marks
        String calculatedGrade =
                calculateGrade(requestDto.getMarks());

        grade.setGrade(calculatedGrade);
        grade.setEnrollment(enrollment);

        Grade savedGrade = gradeRepository.save(grade);

        log.info("Grade created successfully with id: {}",
                savedGrade.getGradeId());

        return gradeMapper.toResponseDto(savedGrade);
    }

    @Override
    public List<GradeResponseDto> getAllGrades() {

        log.info("Fetching all grades");

        return gradeRepository.findAll()
                .stream()
                .map(gradeMapper::toResponseDto)
                .toList();
    }

    @Override
    public GradeResponseDto getGradeById(Long id) {

        log.info("Fetching grade with id: {}", id);

        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Grade not found with id: " + id));

        return gradeMapper.toResponseDto(grade);
    }

    @Override
    public GradeResponseDto updateGrade(
            Long id,
            GradeRequestDto requestDto) {

        log.info("Updating grade with id: {}", id);

        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Grade not found with id: " + id));

        Enrollment enrollment = enrollmentRepository
                .findById(requestDto.getEnrollmentId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Enrollment not found with id: "
                        + requestDto.getEnrollmentId()));

        grade.setMarks(requestDto.getMarks());

        // Automatically calculate grade from updated marks
        String calculatedGrade =
                calculateGrade(requestDto.getMarks());

        grade.setGrade(calculatedGrade);

        grade.setRemarks(requestDto.getRemarks());
        grade.setEnrollment(enrollment);

        Grade updatedGrade =
                gradeRepository.save(grade);

        log.info("Grade updated successfully with id: {}",
                updatedGrade.getGradeId());

        return gradeMapper.toResponseDto(updatedGrade);
    }

    @Override
    public void deleteGrade(Long id) {

        log.info("Deleting grade with id: {}", id);

        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Grade not found with id: " + id));

        gradeRepository.delete(grade);

        log.info("Grade deleted successfully with id: {}", id);
    }

    @Override
    public String calculateGrade(Double  marks) {

        log.info("Calculating grade for marks: {}", marks);

        if (marks == null) {
            throw new IllegalArgumentException(
                    "Marks cannot be null");
        }

        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException(
                    "Marks must be between 0 and 100");
        }

        if (marks >= 90) {
            return "A+";
        } else if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B+";
        } else if (marks >= 60) {
            return "B";
        } else if (marks >= 50) {
            return "C";
        } else {
            return "F";
        }
    }
}