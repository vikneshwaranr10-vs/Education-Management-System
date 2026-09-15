package com.example.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "teachers",
    indexes = {
        @Index(name = "idx_teacher_email", columnList = "email")
    }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @Column(nullable = false)
    private String teacherName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private String specialization;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;
}