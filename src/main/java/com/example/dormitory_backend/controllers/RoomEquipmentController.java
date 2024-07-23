package com.example.dormitory_backend.controllers;

import com.example.dormitory_backend.models.RoomEquipment;
import com.example.dormitory_backend.services.RoomEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/room-equipment")
public class RoomEquipmentController {

    @Autowired
    private RoomEquipmentService roomEquipmentService;

    @GetMapping
    public ResponseEntity<List<RoomEquipment>> getAllRoomEquipments() {
        List<RoomEquipment> roomEquipments = roomEquipmentService.findAll();
        return ResponseEntity.ok(roomEquipments);
    }

    // Get a RoomEquipment by ID
    @GetMapping("/{id}")
    public ResponseEntity<RoomEquipment> getRoomEquipmentById(@PathVariable Integer id) {
        Optional<RoomEquipment> roomEquipment = roomEquipmentService.findById(id);
        if (roomEquipment.isPresent()) {
            return ResponseEntity.ok(roomEquipment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RoomEquipment> createRoomEquipment(@RequestBody RoomEquipment roomEquipment) {
        RoomEquipment savedRoomEquipment = roomEquipmentService.save(roomEquipment);
        return ResponseEntity.status(201).body(savedRoomEquipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomEquipment> updateRoomEquipment(
            @PathVariable Integer id,
            @RequestBody RoomEquipment updatedRoomEquipment) {
        try {
            RoomEquipment updated = roomEquipmentService.update(id, updatedRoomEquipment);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomEquipment(@PathVariable Integer id) {
        try {
            roomEquipmentService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
