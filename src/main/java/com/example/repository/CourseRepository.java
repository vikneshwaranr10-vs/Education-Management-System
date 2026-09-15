package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	List<Course> findByCourseNameContainingIgnoreCase(String courseName);

	List<Course> findByCourseCodeContainingIgnoreCase(String courseCode);

	List<Course> findByDuration(Integer duration);
	
	List<Course> findByTeacherTeacherId(Long teacherId);
}