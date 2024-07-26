package com.example.dormitory_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

/*
Contract = កិច្ចសន្យា

@JsonManagedReference is used to indicate the parent side of a bidirectional relationship.
@JsonBackReference is used to indicate the child side of a bidirectional relationship.

 */
@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_contract;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false)
    @JsonIgnoreProperties("contracts") // Ignore the 'contracts' property in Student
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_dormitory", nullable = false)
    @JsonIgnoreProperties("contracts")
    private Dormitory dormitory;

    @ManyToOne
    @JoinColumn(name = "id_room", nullable = false)
    @JsonIgnoreProperties({"contracts", "dormitory", "invoiceWaterElectricities"})
    private Room room;

    private LocalDate date_start_contract;
    private LocalDate date_end_contract;

    ////
    public Integer getId_contract() {
        return id_contract;
    }

    public void setId_contract(Integer id_contract) {
        this.id_contract = id_contract;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getDate_start_contract() {
        return date_start_contract;
    }

    public void setDate_start_contract(LocalDate date_start_contract) {
        this.date_start_contract = date_start_contract;
    }

    public LocalDate getDate_end_contract() {
        return date_end_contract;
    }

    public void setDate_end_contract(LocalDate date_end_contract) {
        this.date_end_contract = date_end_contract;
    }
}
