package com.example.dormitory_backend.utils;

import com.example.dormitory_backend.models.Contract;

import java.time.LocalDate;
import java.util.List;

public class StudentDTO {

    private Integer id_student;
    private String student_name;
    private Boolean student_sex;
    private String citizenIdentification;
    private LocalDate date_of_birth_student;
    private String student_class;
    private String department_of_student;
    private String student_phone;

    private String student_imageUrl;


    // getter and setter
    public Integer getId_student() {
        return id_student;
    }

    public void setId_student(Integer id_student) {
        this.id_student = id_student;
    }

    public String getStudent_name() {
        return student_name;
    }
    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public Boolean getStudent_sex(){
        return student_sex;
    }

    public String getCitizenIdentification() {
        return citizenIdentification;
    }
    public void setCitizenIdentification(String citizenIdentification) {
        this.citizenIdentification = citizenIdentification;
    }

    public void setStudent_sex(Boolean student_sex){
        this.student_sex = student_sex;
    }
    public LocalDate getDate_of_birth_student() {
        return date_of_birth_student;
    }
    public void setDate_of_birth_student(LocalDate date_of_birth_student) {
        this.date_of_birth_student = date_of_birth_student;
    }
    public String getStudent_class() {
        return student_class;
    }
    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }
    public String getDepartment_of_student() {
        return department_of_student;
    }
    public void setDepartment_of_student(String department_of_student) {
        this.department_of_student = department_of_student;
    }
    public String getStudent_phone() {
        return student_phone;
    }
    public void setStudent_phone(String student_phone) {
        this.student_phone = student_phone;
    }

    public String getStudent_imageUrl(){
        return student_imageUrl;
    }
    public void setStudent_imageUrl(String student_imageUrl){
        this.student_imageUrl = student_imageUrl;
    }

}
