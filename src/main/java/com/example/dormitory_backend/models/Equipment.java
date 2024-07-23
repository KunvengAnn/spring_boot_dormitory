package com.example.dormitory_backend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "equipments")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_equipment;
    private String equipment_name;
    private String equipment_type;    // ( ex: Furniture, Electronic)

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference
    private List<RoomEquipment> roomEquipments;

    ////
    public int getId_equipment() {
        return id_equipment;
    }

    public void setId_equipment(int id_equipment) {
        this.id_equipment = id_equipment;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getEquipment_type() {
        return equipment_type;
    }

    public void setEquipment_type(String equipment_type) {
        this.equipment_type = equipment_type;
    }

    public List<RoomEquipment> getRoomEquipments() {
        return roomEquipments;
    }

    public void setRoomEquipments(List<RoomEquipment> roomEquipments) {
        this.roomEquipments = roomEquipments;
    }
}
