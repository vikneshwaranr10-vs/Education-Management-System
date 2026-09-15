package com.example.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.CourseRequestDto;
import com.example.dto.CourseResponseDto;
import com.example.entity.Course;
import com.example.entity.Teacher;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.CourseMapper;
import com.example.repository.CourseRepository;
import com.example.repository.TeacherRepository;
import com.example.service.CourseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseResponseDto createCourse(CourseRequestDto requestDto) {

        Course course = courseMapper.toEntity(requestDto);

        Teacher teacher = teacherRepository
                .findById(requestDto.getTeacherId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Teacher not found with id: "
                        + requestDto.getTeacherId()));

        course.setTeacher(teacher);

        Course savedCourse = courseRepository.save(course);

        return courseMapper.toResponseDto(savedCourse);
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toResponseDto)
                .toList();
    }

    @Override
    public CourseResponseDto getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Course not found with id: " + id));

        return courseMapper.toResponseDto(course);
    }

    @Override
    public CourseResponseDto updateCourse(
            Long id,
            CourseRequestDto requestDto) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Course not found with id: " + id));

        Teacher teacher = teacherRepository
                .findById(requestDto.getTeacherId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Teacher not found with id: "
                        + requestDto.getTeacherId()));

        course.setCourseName(requestDto.getCourseName());
        course.setCourseCode(requestDto.getCourseCode());
        course.setDescription(requestDto.getDescription());
        course.setDuration(requestDto.getDuration());
        course.setTeacher(teacher);

        Course updatedCourse = courseRepository.save(course);

        return courseMapper.toResponseDto(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Course not found with id: " + id));

        courseRepository.delete(course);
    }

    // Search courses by course name
    @Override
    public List<CourseResponseDto> searchCoursesByName(String name) {

        log.info("Searching courses by name: {}", name);

        return courseRepository
                .findByCourseNameContainingIgnoreCase(name)
                .stream()
                .map(courseMapper::toResponseDto)
                .toList();
    }

    // Search courses by course code
    @Override
    public List<CourseResponseDto> searchCoursesByCode(String code) {

        log.info("Searching courses by code: {}", code);

        return courseRepository
                .findByCourseCodeContainingIgnoreCase(code)
                .stream()
                .map(courseMapper::toResponseDto)
                .toList();
    }

    // Search courses by duration
    @Override
    public List<CourseResponseDto> searchCoursesByDuration(Integer duration) {

        log.info("Searching courses by duration: {}", duration);

        return courseRepository
                .findByDuration(duration)
                .stream()
                .map(courseMapper::toResponseDto)
                .toList();
    }
    
    @Override
    public List<CourseResponseDto> getCoursesByTeacher(Long teacherId) {

        log.info("Fetching courses for teacher id: {}", teacherId);

        teacherRepository.findById(teacherId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Teacher not found with id: " + teacherId));

        return courseRepository
                .findByTeacherTeacherId(teacherId)
                .stream()
                .map(courseMapper::toResponseDto)
                .toList();
    }
}