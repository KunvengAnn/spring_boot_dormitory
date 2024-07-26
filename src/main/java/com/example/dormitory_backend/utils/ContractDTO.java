package com.example.dormitory_backend.utils;

import java.time.LocalDate;

public class ContractDTO {

    private Integer ContractDTOId;
    private Integer studentId;
    private Integer dormitoryId;
    private Integer roomId;
    private LocalDate date_start_contract;
    private LocalDate date_end_contract;

    // Getters and Setters

    public Integer getContractDTOId() {
        return ContractDTOId;
    }

    public void setContractDTOId(Integer contractDTOId) {
        ContractDTOId = contractDTOId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
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
