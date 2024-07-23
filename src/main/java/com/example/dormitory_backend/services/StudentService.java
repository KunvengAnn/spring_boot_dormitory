package com.example.dormitory_backend.services;

import com.example.dormitory_backend.models.Student;
import com.example.dormitory_backend.models.User;
import com.example.dormitory_backend.repositories.StudentRepository;
import com.example.dormitory_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Student> getAllStudents(String email, String token) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent() && existingUser.get().getToken().equals(token)) {
            return studentRepository.findAll();
        } else {
            throw new IllegalArgumentException("Invalid email or token.");
        }
    }

    public Optional<Student> getStudentById(Integer id, String email, String token) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent() && existingUser.get().getToken().equals(token)) {
            return studentRepository.findById(id);
        } else {
            throw new IllegalArgumentException("Invalid email or token.");
        }
    }

    public Optional<Student> getStudentById_no_token(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new IllegalArgumentException("Student not found.");
        }
        return student;
    }

    public Student saveStudent(Student student) {
        try {
            return studentRepository.save(student);
        } catch (Exception e) {
            throw new RuntimeException("Error saving student: " + e.getMessage());
        }
    }

    public void deleteStudent(Integer id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student not found.");
        }
        studentRepository.deleteById(id);
    }
}
