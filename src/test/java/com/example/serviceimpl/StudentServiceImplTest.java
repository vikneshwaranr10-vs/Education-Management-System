package com.example.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.dto.StudentRequestDto;
import com.example.dto.StudentResponseDto;
import com.example.entity.Student;
import com.example.exception.DuplicateResourceException;
import com.example.mapper.StudentMapper;
import com.example.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void getStudentById_ShouldReturnStudent() {

        // Arrange
        Student student = new Student();
        student.setStudentId(1L);
        student.setStudentName("Arun Kumar");
        student.setEmail("arun@gmail.com");

        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setStudentId(1L);
        responseDto.setStudentName("Arun Kumar");
        responseDto.setEmail("arun@gmail.com");

        when(studentRepository.findById(1L))
                .thenReturn(java.util.Optional.of(student));

        when(studentMapper.toResponseDto(student))
                .thenReturn(responseDto);

        // Act
        StudentResponseDto result =
                studentService.getStudentById(1L);

        // Assert
        assertEquals(1L, result.getStudentId());
        assertEquals("Arun Kumar", result.getStudentName());
        assertEquals("arun@gmail.com", result.getEmail());
    }
    
    @Test
    void getStudentById_ShouldThrowException_WhenStudentNotFound() {

        // Arrange
        when(studentRepository.findById(999L))
                .thenReturn(java.util.Optional.empty());

        // Act & Assert
        org.junit.jupiter.api.Assertions.assertThrows(
                com.example.exception.ResourceNotFoundException.class,
                () -> studentService.getStudentById(999L)
        );
    }
    
    @Test
    void createStudent_ShouldReturnStudent() {

        // Arrange
        StudentRequestDto requestDto = new StudentRequestDto();
        requestDto.setStudentName("Vicky");
        requestDto.setEmail("vicky@gmail.com");
        requestDto.setPhone("9876543210");
        requestDto.setAddress("Madurai");

        Student student = new Student();
        student.setStudentId(10L);
        student.setStudentName("Vicky");
        student.setEmail("vicky@gmail.com");
        student.setPhone("9876543210");
        student.setAddress("Madurai");

        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setStudentId(10L);
        responseDto.setStudentName("Vicky");
        responseDto.setEmail("vicky@gmail.com");
        responseDto.setPhone("9876543210");
        responseDto.setAddress("Madurai");

        when(studentMapper.toEntity(requestDto))
                .thenReturn(student);

        when(studentRepository.save(student))
                .thenReturn(student);

        when(studentMapper.toResponseDto(student))
                .thenReturn(responseDto);

        // Act
        StudentResponseDto result =
                studentService.createStudent(requestDto);

        // Assert
        assertEquals(10L, result.getStudentId());
        assertEquals("Vicky", result.getStudentName());
        assertEquals("vicky@gmail.com", result.getEmail());
        
        verify(studentRepository).save(student);
    }
    
    @Test
    void createStudent_ShouldThrowException_WhenEmailAlreadyExists() {

        // Arrange
        StudentRequestDto requestDto = new StudentRequestDto();
        requestDto.setStudentName("Vicky");
        requestDto.setEmail("vicky@gmail.com");
        requestDto.setPhone("9876543210");
        requestDto.setAddress("Chennai");

        when(studentRepository.existsByEmail("vicky@gmail.com"))
                .thenReturn(true);

        // Act & Assert
        assertThrows(
                DuplicateResourceException.class,
                () -> studentService.createStudent(requestDto)
        );

        // Verify that student should not be saved
        verify(studentRepository, org.mockito.Mockito.never())
                .save(org.mockito.ArgumentMatchers.any(Student.class));
    }
    
    @Test
    void updateStudent_ShouldReturnUpdatedStudent() {

        // Arrange
        StudentRequestDto requestDto = new StudentRequestDto();
        requestDto.setStudentName("Vicky Updated");
        requestDto.setEmail("vicky@gmail.com");
        requestDto.setPhone("9876543210");
        requestDto.setAddress("Chennai");

        Student student = new Student();
        student.setStudentId(10L);
        student.setStudentName("Vicky");
        student.setEmail("vicky@gmail.com");
        student.setPhone("9876543210");
        student.setAddress("Madurai");

        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setStudentId(10L);
        responseDto.setStudentName("Vicky Updated");
        responseDto.setEmail("vicky@gmail.com");
        responseDto.setPhone("9876543210");
        responseDto.setAddress("Chennai");

        when(studentRepository.findById(10L))
                .thenReturn(java.util.Optional.of(student));

        when(studentRepository.save(student))
                .thenReturn(student);

        when(studentMapper.toResponseDto(student))
                .thenReturn(responseDto);

        // Act
        StudentResponseDto result =
                studentService.updateStudent(10L, requestDto);

        // Assert
        assertEquals(10L, result.getStudentId());
        assertEquals("Vicky Updated", result.getStudentName());
        assertEquals("Chennai", result.getAddress());

        verify(studentRepository).findById(10L);
        verify(studentRepository).save(student);
    }
    
    @Test
    void updateStudent_ShouldThrowException_WhenStudentNotFound() {

        // Arrange
        StudentRequestDto requestDto = new StudentRequestDto();
        requestDto.setStudentName("Vicky Updated");
        requestDto.setEmail("vicky@gmail.com");
        requestDto.setPhone("9876543210");
        requestDto.setAddress("Chennai");

        when(studentRepository.findById(999L))
                .thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(
                com.example.exception.ResourceNotFoundException.class,
                () -> studentService.updateStudent(999L, requestDto)
        );

        verify(studentRepository).findById(999L);

        verify(studentRepository,
                org.mockito.Mockito.never())
                .save(org.mockito.ArgumentMatchers.any(Student.class));
    }
    
    @Test
    void deleteStudent_ShouldDeleteStudent_WhenStudentExists() {

        // Arrange
        Student student = new Student();
        student.setStudentId(10L);

        when(studentRepository.findById(10L))
                .thenReturn(java.util.Optional.of(student));

        // Act
        studentService.deleteStudent(10L);

        // Assert
        verify(studentRepository).findById(10L);
        verify(studentRepository).delete(student);
    }
    
    @Test
    void deleteStudent_ShouldThrowException_WhenStudentNotFound() {

        // Arrange
        when(studentRepository.findById(999L))
                .thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(
                com.example.exception.ResourceNotFoundException.class,
                () -> studentService.deleteStudent(999L)
        );

        verify(studentRepository).findById(999L);

        verify(studentRepository,
                org.mockito.Mockito.never())
                .delete(org.mockito.ArgumentMatchers.any(Student.class));
    }
}


//1. Get Student — Success              ✅
//2. Get Student — Not Found            ✅
//3. Create Student — Success           ✅
//4. Create Student — Duplicate Email   ✅
//5. Update Student — Success           ✅
//6. Update Student — Not Found         ✅
//7. Delete Student — Success           ✅
//8. Delete Student — Not Found         ✅