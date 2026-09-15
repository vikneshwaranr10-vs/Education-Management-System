package com.example.serviceimpl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.dto.StudentRequestDto;
import com.example.dto.StudentResponseDto;
import com.example.entity.Student;
import com.example.exception.DuplicateResourceException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.StudentMapper;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponseDto createStudent(StudentRequestDto requestDto) {

        log.info("Creating student with email: {}", requestDto.getEmail());

        if (studentRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateResourceException(
                "Student already exists with email: " + requestDto.getEmail()
            );
        }
        
        Student student = studentMapper.toEntity(requestDto);
        Student savedStudent = studentRepository.save(student);

        log.info("Student created successfully with id: {}",
                savedStudent.getStudentId());

        return studentMapper.toResponseDto(savedStudent);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {

        log.info("Fetching all students");

        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponseDto)
                .toList();
    }
    
    @Cacheable(value = "students", key = "#id")
    @Override
    public StudentResponseDto getStudentById(Long id) {
    	
        log.info("Fetching student with id: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Student not found with id: {}", id);
                    return new ResourceNotFoundException(
                            "Student not found with id: " + id);
                });

        return studentMapper.toResponseDto(student);
    }

    @CachePut(value = "students", key = "#id")
    @Override
    public StudentResponseDto updateStudent(
            Long id,
            StudentRequestDto requestDto) {

        log.info("Updating student with id: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Student not found with id: {}", id);
                    return new ResourceNotFoundException(
                            "Student not found with id: " + id);
                });

        student.setStudentName(requestDto.getStudentName());
        student.setEmail(requestDto.getEmail());
        student.setPhone(requestDto.getPhone());
        student.setAddress(requestDto.getAddress());
        student.setDateOfBirth(requestDto.getDateOfBirth());

        Student updatedStudent = studentRepository.save(student);

        log.info("Student updated successfully with id: {}",
                updatedStudent.getStudentId());

        return studentMapper.toResponseDto(updatedStudent);
    }

    @CacheEvict(value = "students", key = "#id")
    @Override
    public void deleteStudent(Long id) {

        log.info("Deleting student with id: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Student not found with id: {}", id);
                    return new ResourceNotFoundException(
                            "Student not found with id: " + id);
                });

        studentRepository.delete(student);

        log.info("Student deleted successfully with id: {}", id);
    }

    @Override
    public List<StudentResponseDto> searchStudentsByName(String name) {

        log.info("Searching students by name: {}", name);

        return studentRepository.findStudentsByName(name)
                .stream()
                .map(studentMapper::toResponseDto)
                .toList();
    }

    @Override
    public StudentResponseDto searchStudentByEmail(String email) {

        log.info("Searching student by email: {}", email);

        Student student = studentRepository.findByEmailNative(email)
                .orElseThrow(() -> {
                    log.error("Student not found with email: {}", email);
                    return new ResourceNotFoundException(
                            "Student not found with email: " + email);
                });

        return studentMapper.toResponseDto(student);
    }

    @Override
    public Page<StudentResponseDto> getStudentsWithPagination(
            int page,
            int size,
            String sortBy,
            String direction) {

        log.info("Fetching students - page: {}, size: {}, sortBy: {}, direction: {}",
                page, size, sortBy, direction);

        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        return studentRepository.findAll(pageable)
                .map(studentMapper::toResponseDto);
    }
}