package com.example.dormitory_backend.controllers;

import com.example.dormitory_backend.models.Equipment;
import com.example.dormitory_backend.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        try {
            List<Equipment> equipmentList = equipmentService.getAll();
            return ResponseEntity.ok(equipmentList);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable int id) {
        try {
            Optional<Equipment> equipment = equipmentService.getById(id);
            return equipment.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment) {
        try {
            Equipment savedEquipment = equipmentService.save(equipment);
            return ResponseEntity.status(201).body(savedEquipment);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@RequestBody Equipment equipment, @PathVariable Integer id) {
        try {
            Equipment updatedEquipment = equipmentService.updateEquipment(id, equipment);
            if (updatedEquipment != null) {
                return ResponseEntity.ok(updatedEquipment);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Integer id) {
        try {
            equipmentService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
