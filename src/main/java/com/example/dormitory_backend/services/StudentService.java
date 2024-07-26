package com.example.dormitory_backend.services;

import com.example.dormitory_backend.models.Contract;
import com.example.dormitory_backend.models.Student;
import com.example.dormitory_backend.models.User;
import com.example.dormitory_backend.repositories.StudentRepository;
import com.example.dormitory_backend.repositories.UserRepository;
import com.example.dormitory_backend.utils.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById_no_token(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new IllegalArgumentException("Student not found.");
        }
        return student;
    }

    public Student saveStudent(StudentDTO StDTO) {
        try {
            Student student = new Student();
            student.setId_student(StDTO.getId_student());
            student.setStudent_name(StDTO.getStudent_name());
            student.setContracts(null);

            student.setStudent_phone(StDTO.getStudent_phone());
            student.setStudent_sex(StDTO.getStudent_sex());
            student.setStudent_class(StDTO.getStudent_class());
            student.setDepartment_of_student(StDTO.getDepartment_of_student());
            student.setDate_of_birth_student(StDTO.getDate_of_birth_student());

            return studentRepository.save(student);
        } catch (Exception e) {
            throw new RuntimeException("Error saving student: " + e.getMessage());
        }
    }
    public Student updateStudent(Integer id, StudentDTO StDTO) {
        try {
            Optional<Student> optionalStudent = studentRepository.findById(id);
            if (optionalStudent.isEmpty()) {
                throw new IllegalArgumentException("Student not found.");
            }

            Student student = optionalStudent.get();
            student.setStudent_name(StDTO.getStudent_name());
            student.setStudent_phone(StDTO.getStudent_phone());
            student.setStudent_sex(StDTO.getStudent_sex());
            student.setStudent_class(StDTO.getStudent_class());
            student.setDepartment_of_student(StDTO.getDepartment_of_student());
            student.setDate_of_birth_student(StDTO.getDate_of_birth_student());

            return studentRepository.save(student);
        } catch (Exception e) {
            throw new RuntimeException("Error updating student: " + e.getMessage());
        }
    }

    public void deleteStudent(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
        } else {
            throw new IllegalArgumentException("Student not found.");
        }
    }
}
