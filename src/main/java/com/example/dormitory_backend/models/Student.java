package com.example.dormitory_backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_student;
    private String student_name;
    private Boolean student_sex;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date_of_birth_student;
    private String student_class;
    private String department_of_student; // (VietName Khoa)
    private String student_phone;
    private String citizenIdentification;

    private String student_imageUrl;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("student")
    private List<Contract> contracts;

    //
    public Integer getId_student() {
        return id_student;
    }

    public void setId_student(Integer id_student) {
        this.id_student = id_student;
    }

    public String getCitizenIdentification() {
        return citizenIdentification;
    }

    public void setCitizenIdentification(String citizenIdentification) {
        this.citizenIdentification = citizenIdentification;
    }

    ///
    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
    public String getStudent_name() {
        return student_name;
    }
    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
    public void setStudent_sex(Boolean student_sex){
        this.student_sex = student_sex;
    }
    public Boolean getStudent_sex(){
        return student_sex;
    }
    public Date getDate_of_birth_student() {
        return date_of_birth_student;
    }
    public void setDate_of_birth_student(Date date_of_birth_student) {
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

    public String getStudent_imageUrl() {
        return student_imageUrl;
    }
    public void setStudent_imageUrl(String student_imageUrl) {
        this.student_imageUrl = student_imageUrl;
    }
}
