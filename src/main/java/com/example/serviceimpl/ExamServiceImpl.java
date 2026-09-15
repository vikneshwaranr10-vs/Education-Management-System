package com.example.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.ExamRequestDto;
import com.example.dto.ExamResponseDto;
import com.example.entity.Course;
import com.example.entity.Exam;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.ExamMapper;
import com.example.repository.CourseRepository;
import com.example.repository.ExamRepository;
import com.example.service.ExamService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;
    private final ExamMapper examMapper;

    @Override
    public ExamResponseDto createExam(ExamRequestDto requestDto) {

        Exam exam = examMapper.toEntity(requestDto);

        Course course = courseRepository
                .findById(requestDto.getCourseId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Course not found with id: "
                        + requestDto.getCourseId()));

        exam.setCourse(course);

        Exam savedExam = examRepository.save(exam);

        return examMapper.toResponseDto(savedExam);
    }

    @Override
    public List<ExamResponseDto> getAllExams() {

        return examRepository.findAll()
                .stream()
                .map(examMapper::toResponseDto)
                .toList();
    }

    @Override
    public ExamResponseDto getExamById(Long id) {

        Exam exam = examRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Exam not found with id: " + id));

        return examMapper.toResponseDto(exam);
    }

    @Override
    public ExamResponseDto updateExam(
            Long id, ExamRequestDto requestDto) {

        Exam exam = examRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Exam not found with id: " + id));

        Course course = courseRepository
                .findById(requestDto.getCourseId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Course not found with id: "
                        + requestDto.getCourseId()));

        exam.setExamName(requestDto.getExamName());
        exam.setExamDate(requestDto.getExamDate());
        exam.setTotalMarks(requestDto.getTotalMarks());
        exam.setCourse(course);

        Exam updatedExam = examRepository.save(exam);

        return examMapper.toResponseDto(updatedExam);
    }

    @Override
    public void deleteExam(Long id) {

        Exam exam = examRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Exam not found with id: " + id));

        examRepository.delete(exam);
    }
}