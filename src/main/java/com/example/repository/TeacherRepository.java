package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}