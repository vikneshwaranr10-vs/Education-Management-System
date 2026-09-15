package com.example.service;

import java.util.List;

import com.example.dto.CourseRequestDto;
import com.example.dto.CourseResponseDto;

public interface CourseService {

    CourseResponseDto createCourse(CourseRequestDto requestDto);

    List<CourseResponseDto> getAllCourses();

    CourseResponseDto getCourseById(Long id);

    CourseResponseDto updateCourse(Long id, CourseRequestDto requestDto);

    void deleteCourse(Long id);
    
    List<CourseResponseDto> searchCoursesByName(String name);

    List<CourseResponseDto> searchCoursesByCode(String code);

    List<CourseResponseDto> searchCoursesByDuration(Integer duration);
    
    List<CourseResponseDto> getCoursesByTeacher(Long teacherId);
}