package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // JPQL Query - Search student by name
    @Query("SELECT s FROM Student s WHERE s.studentName = :name")
    List<Student> findStudentsByName(@Param("name") String name);

    // Native SQL Query - Search student by email
    @Query(
        value = "SELECT * FROM students WHERE email = :email",
        nativeQuery = true
    )
    Optional<Student> findByEmailNative(@Param("email") String email);
    
    boolean existsByEmail(String email);
}