package com.example.dormitory_backend.repositories;

import com.example.dormitory_backend.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}