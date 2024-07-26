package com.example.dormitory_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_room;
    private Integer room_number;
    private Double room_price;
    private Integer room_max_capacity;
    private Integer quantity_person;
    private Boolean status_is_empty_room;
    //nullable
    @Column(nullable = true)
    private Boolean status_room_is_boy;

    @ManyToOne
    @JoinColumn(name = "id_dormitory", nullable = false)
    @JsonIgnoreProperties({"rooms", "contracts"})
    private Dormitory dormitory;

    // this mean one room can have many contract ( VietNames is Hop Dong )
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("room")
    private List<Contract> contracts;

    //
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonIgnoreProperties({"room", "detailInvoiceWaterElectricities"})
    @JsonIgnoreProperties("room")
    private List<Invoice_water_electricity> invoiceWaterElectricities;

    //
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("room")
    private List<RoomEquipment> roomEquipments;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("room")
    private List<Water_electricity> waterElectricities;


    public Integer getId_room() {
        return id_room;
    }

    public void setId_room(Integer id_room) {
        this.id_room = id_room;
    }

    public Integer getRoom_number() {
        return room_number;
    }

    public void setRoom_number(Integer room_number) {
        this.room_number = room_number;
    }

    public Double getRoom_price() {
        return room_price;
    }

    public void setRoom_price(Double room_price) {
        this.room_price = room_price;
    }

    public Integer getRoom_max_capacity() {
        return room_max_capacity;
    }

    public void setRoom_max_capacity(Integer room_max_capacity) {
        this.room_max_capacity = room_max_capacity;
    }

    public Integer getQuantity_person() {
        return quantity_person;
    }

    public void setQuantity_person(Integer quantity_person) {
        this.quantity_person = quantity_person;
    }

    public boolean isStatus_is_empty_room() {
        return status_is_empty_room;
    }

    public void setStatus_is_empty_room(boolean status_is_empty_room) {
        this.status_is_empty_room = status_is_empty_room;
    }

    public boolean isStatus_room_is_boy() {
        return status_room_is_boy;
    }

    public void setStatus_room_is_boy(boolean status_room_is_boy) {
        this.status_room_is_boy = status_room_is_boy;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<Invoice_water_electricity> getInvoiceWaterElectricities() {
        return invoiceWaterElectricities;
    }

    public void setInvoiceWaterElectricities(List<Invoice_water_electricity> invoiceWaterElectricities) {
        this.invoiceWaterElectricities = invoiceWaterElectricities;
    }

    public List<RoomEquipment> getRoomEquipments() {
        return roomEquipments;
    }

    public void setRoomEquipments(List<RoomEquipment> roomEquipments) {
        this.roomEquipments = roomEquipments;
    }
    public List<Water_electricity> getWaterElectricities() {
        return waterElectricities;
    }
    public void setWaterElectricities(List<Water_electricity> waterElectricities) {
        this.waterElectricities = waterElectricities;
    }
}
