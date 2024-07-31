package com.example.dormitory_backend.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadDto {
    private MultipartFile file;
    private StudentDTO studentDTO;

    // Getters and setters
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }
}
