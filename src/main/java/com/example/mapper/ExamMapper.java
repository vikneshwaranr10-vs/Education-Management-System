package com.example.mapper;

import org.springframework.stereotype.Component;

import com.example.dto.ExamRequestDto;
import com.example.dto.ExamResponseDto;
import com.example.entity.Exam;

@Component
public class ExamMapper {

    public Exam toEntity(ExamRequestDto dto) {

        Exam exam = new Exam();

        exam.setExamName(dto.getExamName());
        exam.setExamDate(dto.getExamDate());
        exam.setTotalMarks(dto.getTotalMarks());

        return exam;
    }

    public ExamResponseDto toResponseDto(Exam exam) {

        ExamResponseDto dto = new ExamResponseDto();

        dto.setExamId(exam.getExamId());
        dto.setExamName(exam.getExamName());
        dto.setExamDate(exam.getExamDate());
        dto.setTotalMarks(exam.getTotalMarks());

        if (exam.getCourse() != null) {
            dto.setCourseId(
                    exam.getCourse().getCourseId());

            dto.setCourseName(
                    exam.getCourse().getCourseName());
        }

        return dto;
    }
}