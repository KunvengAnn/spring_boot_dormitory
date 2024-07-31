package com.example.dormitory_backend.services;

import com.example.dormitory_backend.models.Contract;
import com.example.dormitory_backend.models.Student;
import com.example.dormitory_backend.models.User;
import com.example.dormitory_backend.repositories.ContractRepository;
import com.example.dormitory_backend.repositories.StudentRepository;
import com.example.dormitory_backend.repositories.UserRepository;
import com.example.dormitory_backend.utils.FileUploadDto;
import com.example.dormitory_backend.utils.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

    // Method to handle file upload with unique file name
    public String uploadImage(MultipartFile file) {
        // Check if the file is empty
        if (file.isEmpty()) {
            throw new IllegalArgumentException("The file is empty.");
        }

        // Generate a unique file name
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        // Define the directory to save the uploaded files
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/uploads"; // Absolute path based on project directory
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs(); // Create directories if they don't exist
        }

        String filePath = uploadDir + File.separator + uniqueFileName;

        // Save file to the defined location
        try {
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);
            return  uniqueFileName;
        } catch (IOException e) {
            throw new RuntimeException("Error saving file: " + e.getMessage());
        }
    }


    public Student saveStudent(FileUploadDto fileUploadDto) {
        try {
            StudentDTO StDTO = fileUploadDto.getStudentDTO();
            MultipartFile imageFile = fileUploadDto.getFile();

            Student student = new Student();
            student.setId_student(StDTO.getId_student());
            student.setStudent_name(StDTO.getStudent_name());
            student.setStudent_phone(StDTO.getStudent_phone());
            student.setStudent_sex(StDTO.getStudent_sex());
            student.setStudent_class(StDTO.getStudent_class());
            student.setDepartment_of_student(StDTO.getDepartment_of_student());
            student.setDate_of_birth_student(StDTO.getDate_of_birth_student());

            // Handle image file
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageUrl = uploadImage(imageFile);
                student.setStudent_imageUrl(imageUrl);
            }

            return studentRepository.save(student);
        } catch (Exception e) {
            throw new RuntimeException("Error saving student: " + e.getMessage());
        }
    }

    public Student updateStudent(Integer id, FileUploadDto fileUploadDto) {
        try {
            StudentDTO StDTO = fileUploadDto.getStudentDTO();
            MultipartFile imageFile = fileUploadDto.getFile();

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

            student.setContracts(optionalStudent.get().getContracts());

            // Handle image file
            if (imageFile != null && !imageFile.isEmpty()) {
                // Remove old image file if exists
                String existingImageUrl = student.getStudent_imageUrl();
                if (existingImageUrl != null && !existingImageUrl.isEmpty()) {
                    // Construct the file path of the existing image
                    File existingFile = new File(System.getProperty("user.dir") + "/src/main/resources/static/uploads/" + existingImageUrl);
                    if (existingFile.exists()) {
                        existingFile.delete(); // Delete the existing image file
                    }
                }

                // Save the new image file
                String newImageUrl = uploadImage(imageFile);
                student.setStudent_imageUrl(newImageUrl);
            }

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
