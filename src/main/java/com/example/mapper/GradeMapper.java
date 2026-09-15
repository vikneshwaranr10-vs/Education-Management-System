package com.example.mapper;

import org.springframework.stereotype.Component;

import com.example.dto.GradeRequestDto;
import com.example.dto.GradeResponseDto;
import com.example.entity.Grade;

@Component
public class GradeMapper {

    public Grade toEntity(GradeRequestDto dto) {

        Grade grade = new Grade();

        grade.setMarks(dto.getMarks());
        grade.setGrade(dto.getGrade());
        grade.setRemarks(dto.getRemarks());

        return grade;
    }

    public GradeResponseDto toResponseDto(Grade grade) {

        GradeResponseDto dto = new GradeResponseDto();

        dto.setGradeId(grade.getGradeId());
        dto.setMarks(grade.getMarks());
        dto.setGrade(grade.getGrade());
        dto.setRemarks(grade.getRemarks());

        if (grade.getEnrollment() != null) {

            dto.setEnrollmentId(
                    grade.getEnrollment().getEnrollmentId());

            if (grade.getEnrollment().getStudent() != null) {
                dto.setStudentId(
                        grade.getEnrollment()
                                .getStudent()
                                .getStudentId());

                dto.setStudentName(
                        grade.getEnrollment()
                                .getStudent()
                                .getStudentName());
            }

            if (grade.getEnrollment().getCourse() != null) {
                dto.setCourseId(
                        grade.getEnrollment()
                                .getCourse()
                                .getCourseId());

                dto.setCourseName(
                        grade.getEnrollment()
                                .getCourse()
                                .getCourseName());
            }
        }

        return dto;
    }
}