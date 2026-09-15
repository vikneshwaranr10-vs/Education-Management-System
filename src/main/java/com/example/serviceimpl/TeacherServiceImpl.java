package com.example.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.TeacherRequestDto;
import com.example.dto.TeacherResponseDto;
import com.example.entity.Teacher;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.TeacherMapper;
import com.example.repository.TeacherRepository;
import com.example.service.TeacherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public TeacherResponseDto createTeacher(TeacherRequestDto requestDto) {

        Teacher teacher = teacherMapper.toEntity(requestDto);

        Teacher savedTeacher = teacherRepository.save(teacher);

        return teacherMapper.toResponseDto(savedTeacher);
    }

    @Override
    public List<TeacherResponseDto> getAllTeachers() {

        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toResponseDto)
                .toList();
    }

    @Override
    public TeacherResponseDto getTeacherById(Long id) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Teacher not found with id: " + id));

        return teacherMapper.toResponseDto(teacher);
    }

    @Override
    public TeacherResponseDto updateTeacher(
            Long id, TeacherRequestDto requestDto) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Teacher not found with id: " + id));

        teacher.setTeacherName(requestDto.getTeacherName());
        teacher.setEmail(requestDto.getEmail());
        teacher.setPhone(requestDto.getPhone());
        teacher.setSpecialization(requestDto.getSpecialization());

        Teacher updatedTeacher = teacherRepository.save(teacher);

        return teacherMapper.toResponseDto(updatedTeacher);
    }

    @Override
    public void deleteTeacher(Long id) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Teacher not found with id: " + id));

        teacherRepository.delete(teacher);
    }
}