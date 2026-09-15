package com.example.serviceimpl;

import org.springframework.stereotype.Service;

import com.example.dto.DashboardResponseDto;
import com.example.repository.CourseRepository;
import com.example.repository.EnrollmentRepository;
import com.example.repository.ExamRepository;
import com.example.repository.GradeRepository;
import com.example.repository.StudentRepository;
import com.example.repository.TeacherRepository;
import com.example.service.DashboardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final ExamRepository examRepository;
    private final GradeRepository gradeRepository;

    @Override
    public DashboardResponseDto getDashboard() {

        log.info("Fetching education dashboard statistics");

        long totalStudents = studentRepository.count();
        long totalTeachers = teacherRepository.count();
        long totalCourses = courseRepository.count();
        long totalEnrollments = enrollmentRepository.count();
        long totalExams = examRepository.count();
        long totalGrades = gradeRepository.count();

        return new DashboardResponseDto(
                totalStudents,
                totalTeachers,
                totalCourses,
                totalEnrollments,
                totalExams,
                totalGrades
        );
    }
}