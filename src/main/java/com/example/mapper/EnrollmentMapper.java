package com.example.mapper;

import org.springframework.stereotype.Component;

import com.example.dto.EnrollmentRequestDto;
import com.example.dto.EnrollmentResponseDto;
import com.example.entity.Enrollment;

@Component
public class EnrollmentMapper {

    public Enrollment toEntity(EnrollmentRequestDto dto) {

        Enrollment enrollment = new Enrollment();

        enrollment.setEnrollmentDate(dto.getEnrollmentDate());
        enrollment.setStatus(dto.getStatus());

        return enrollment;
    }

    public EnrollmentResponseDto toResponseDto(Enrollment enrollment) {

        EnrollmentResponseDto dto = new EnrollmentResponseDto();

        dto.setEnrollmentId(enrollment.getEnrollmentId());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        dto.setStatus(enrollment.getStatus());

        if (enrollment.getStudent() != null) {
            dto.setStudentId(
                    enrollment.getStudent().getStudentId());

            dto.setStudentName(
                    enrollment.getStudent().getStudentName());
        }

        if (enrollment.getCourse() != null) {
            dto.setCourseId(
                    enrollment.getCourse().getCourseId());

            dto.setCourseName(
                    enrollment.getCourse().getCourseName());
        }

        return dto;
    }
}