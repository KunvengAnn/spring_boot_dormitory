package com.example.dormitory_backend.controllers;

import com.example.dormitory_backend.models.Contract;
import com.example.dormitory_backend.models.Student;
import com.example.dormitory_backend.services.StudentService;
import com.example.dormitory_backend.utils.FileUploadDto;
import com.example.dormitory_backend.utils.StudentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Object> saveStudent(
            @RequestParam("file") MultipartFile file,
            @RequestParam("studentDTO") String studentDTOJson
    ) {
        try {
            // Convert studentDTOJson string to StudentDTO
            ObjectMapper objectMapper = new ObjectMapper();
            StudentDTO studentDTO = objectMapper.readValue(studentDTOJson, StudentDTO.class);

            // Wrap everything into FileUploadDto
            FileUploadDto fileUploadDto = new FileUploadDto();
            fileUploadDto.setFile(file);
            fileUploadDto.setStudentDTO(studentDTO);

            Student savedStudent = studentService.saveStudent(fileUploadDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
        } catch (DataIntegrityViolationException e) {
            logger.error("Data integrity violation: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data integrity violation: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Invalid argument: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid argument: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error saving student: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            logger.error("Error retrieving students", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        try {
            Optional<Student> student = studentService.getStudentById_no_token(id);
            return student.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        } catch (Exception e) {
            logger.error("Error retrieving student", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(
            @PathVariable Integer id,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("studentDTO") String studentDTOJson
    ) {
        try {
            // Convert studentDTOJson string to StudentDTO
            ObjectMapper objectMapper = new ObjectMapper();
            StudentDTO studentDTO = objectMapper.readValue(studentDTOJson, StudentDTO.class);

            // Wrap everything into FileUploadDto
            FileUploadDto fileUploadDto = new FileUploadDto();
            fileUploadDto.setFile(file);
            fileUploadDto.setStudentDTO(studentDTO);

            Student updatedStudent = studentService.updateStudent(id, fileUploadDto);
            return ResponseEntity.ok(updatedStudent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            logger.error("Data integrity violation: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data integrity violation: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error updating student", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error deleting student", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the student.");
        }
    }
}