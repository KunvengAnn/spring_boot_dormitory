package com.example.dormitory_backend.controllers;

import com.example.dormitory_backend.models.Room;
import com.example.dormitory_backend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        try {
            List<Room> rooms = roomService.getAllRooms();
            return ResponseEntity.ok(rooms);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Integer id) {
        try {
            Optional<Room> room = roomService.getRoomById(id);
            return room.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        try {
            Room savedRoom = roomService.saveRoom(room);
            return ResponseEntity.status(201).body(savedRoom);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Integer id, @RequestBody Room roomDetails) {
        try {
            Room updatedRoom = roomService.updateRoom(id, roomDetails);
            if (updatedRoom != null) {
                return ResponseEntity.ok(updatedRoom);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Integer id) {
        try {
            roomService.deleteRoomById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
