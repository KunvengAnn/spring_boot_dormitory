package com.example.dormitory_backend.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "water_electricitys")
public class Water_electricity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_water_electricity;

    @ManyToOne
    @JoinColumn(name = "id_room", nullable = false)
    private Room room;

    private Double quantity_electricity_use_start;
    private Double quantity_electricity_use_end;
    private Double quantity_water_use_start;
    private Double quantity_water_use_end;

    private LocalDate date_note;

    // Getters and Setters

    public Integer getId_water_electricity() {
        return id_water_electricity;
    }

    public void setId_water_electricity(Integer id_water_electricity) {
        this.id_water_electricity = id_water_electricity;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Double getQuantity_electricity_use_start() {
        return quantity_electricity_use_start;
    }

    public void setQuantity_electricity_use_start(Double quantity_electricity_use_start) {
        this.quantity_electricity_use_start = quantity_electricity_use_start;
    }

    public Double getQuantity_electricity_use_end() {
        return quantity_electricity_use_end;
    }

    public void setQuantity_electricity_use_end(Double quantity_electricity_use_end) {
        this.quantity_electricity_use_end = quantity_electricity_use_end;
    }

    public Double getQuantity_water_use_start() {
        return quantity_water_use_start;
    }

    public void setQuantity_water_use_start(Double quantity_water_use_start) {
        this.quantity_water_use_start = quantity_water_use_start;
    }

    public Double getQuantity_water_use_end() {
        return quantity_water_use_end;
    }

    public void setQuantity_water_use_end(Double quantity_water_use_end) {
        this.quantity_water_use_end = quantity_water_use_end;
    }

    public LocalDate getDate_note() {
        return date_note;
    }

    public void setDate_note(LocalDate date_note) {
        this.date_note = date_note;
    }
}
