package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

}