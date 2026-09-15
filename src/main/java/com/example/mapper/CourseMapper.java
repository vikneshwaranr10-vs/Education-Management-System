package com.example.mapper;

import org.springframework.stereotype.Component;

import com.example.dto.CourseRequestDto;
import com.example.dto.CourseResponseDto;
import com.example.entity.Course;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequestDto dto) {

        Course course = new Course();

        course.setCourseName(dto.getCourseName());
        course.setCourseCode(dto.getCourseCode());
        course.setDescription(dto.getDescription());
        course.setDuration(dto.getDuration());

        return course;
    }

    public CourseResponseDto toResponseDto(Course course) {

        CourseResponseDto dto = new CourseResponseDto();

        dto.setCourseId(course.getCourseId());
        dto.setCourseName(course.getCourseName());
        dto.setCourseCode(course.getCourseCode());
        dto.setDescription(course.getDescription());
        dto.setDuration(course.getDuration());

        if (course.getTeacher() != null) {
            dto.setTeacherId(course.getTeacher().getTeacherId());
            dto.setTeacherName(course.getTeacher().getTeacherName());
        }

        return dto;
    }
}