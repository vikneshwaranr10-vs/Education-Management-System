package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

	List<Enrollment> findByStudentStudentId(Long studentId);
	
	List<Enrollment> findByCourseCourseId(Long courseId);
}