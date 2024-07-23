package com.example.dormitory_backend.controllers;

import com.example.dormitory_backend.models.Contract;
import com.example.dormitory_backend.models.Student;
import com.example.dormitory_backend.services.StudentService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(@RequestHeader("email") String email, @RequestHeader("token") String token) {
        try {
            List<Student> students = studentService.getAllStudents(email, token);
            return ResponseEntity.ok(students);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Unauthorized
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Internal Server Error
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id, @RequestHeader("email") String email, @RequestHeader("token") String token) {
        try {
            Optional<Student> student = studentService.getStudentById(id, email, token);
            return student.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Not Found
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Unauthorized
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Internal Server Error
        }
    }

    @PostMapping
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        try {
            if (student.getStudent_name() == null || student.getStudent_name().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Student name is required.");
            }
            // Additional validation can be added here

            Student createdStudent = studentService.saveStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving student: " + e.getMessage()); // Bad Request
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable Integer id, @RequestBody Student studentDetails) {
        try {
            Optional<Student> studentOpt = studentService.getStudentById_no_token(id);
            if (studentOpt.isPresent()) {
                Student existingStudent = studentOpt.get();
                existingStudent.setStudent_name(studentDetails.getStudent_name());
                existingStudent.setStudent_sex(studentDetails.getStudent_sex());
                existingStudent.setDate_of_birth_student(studentDetails.getDate_of_birth_student());
                existingStudent.setStudent_class(studentDetails.getStudent_class());
                existingStudent.setDepartment_of_student(studentDetails.getDepartment_of_student());
                existingStudent.setStudent_phone(studentDetails.getStudent_phone());

                // Clear existing contracts
                existingStudent.getContracts().clear();

                List<Contract> newContracts = studentDetails.getContracts();
                for (Contract newContract : newContracts) {
                    newContract.setStudent(existingStudent);
                    existingStudent.getContracts().add(newContract);
                }

                return ResponseEntity.ok(studentService.saveStudent(existingStudent));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request data.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating student: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Integer id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found."); // Not Found
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting student: " + e.getMessage()); // Internal Server Error
        }
    }
}
