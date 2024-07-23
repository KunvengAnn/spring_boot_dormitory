package com.example.dormitory_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "dormitorys")
public class Dormitory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_dormitory;
    private String dormitory_name;

    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference // Managed side of the relationship
    private List<Room> rooms;

    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference // Managed side of the relationship
    private List<Contract> contracts;

    public Integer getId_dormitory() {
        return id_dormitory;
    }

    public void setId_dormitory(Integer id_dormitory) {
        this.id_dormitory = id_dormitory;
    }

    public String getDormitory_name() {
        return dormitory_name;
    }

    public void setDormitory_name(String dormitory_name) {
        this.dormitory_name = dormitory_name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
}
