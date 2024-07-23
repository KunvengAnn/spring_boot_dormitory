package com.example.dormitory_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "roomequipments")
public class RoomEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_room_equipment;

    @ManyToOne
    @JoinColumn(name = "id_equipment", nullable = false)
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "id_room", nullable = false)
    //@JsonBackReference
    private Room room;

    private Integer quantity_equipment;
    private Boolean status;  // ( ex: like old, new, broken )

    ////
    public Integer getId_room_equipment() {
        return id_room_equipment;
    }

    public void setId_room_equipment(Integer id_room_equipment) {
        this.id_room_equipment = id_room_equipment;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Integer getQuantity_equipment() {
        return quantity_equipment;
    }

    public void setQuantity_equipment(Integer quantity_equipment) {
        this.quantity_equipment = quantity_equipment;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
