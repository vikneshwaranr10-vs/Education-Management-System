package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {

}