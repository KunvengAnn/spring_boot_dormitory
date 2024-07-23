package com.example.dormitory_backend.controllers;

import com.example.dormitory_backend.models.Dormitory;
import com.example.dormitory_backend.services.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/dormitory")
public class DormitoryController {

    @Autowired
    private DormitoryService dormitoryService;

    @GetMapping
    public ResponseEntity<List<Dormitory>> getAllDormitories() {
        try {
            List<Dormitory> dormitories = dormitoryService.getAllDormitory();
            return ResponseEntity.ok(dormitories);
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Internal Server Error
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dormitory> getDormitoryById(@PathVariable Integer id) {
        try {
            Optional<Dormitory> dormitory = dormitoryService.getDormitoryById(id);
            return dormitory.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Internal Server Error
        }
    }

    @PostMapping
    public ResponseEntity<Dormitory> createDormitory(@RequestBody Dormitory dormitory) {
        try {
            Dormitory savedDormitory = dormitoryService.saveDormitory(dormitory);
            return ResponseEntity.status(201).body(savedDormitory); // Created
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Internal Server Error
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dormitory> updateDormitory(@RequestBody Dormitory dormitory, @PathVariable Integer id) {
        try {
            Dormitory updatedDormitory = dormitoryService.updateDormitory(id, dormitory);
            if (updatedDormitory != null) {
                return ResponseEntity.ok(updatedDormitory);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Internal Server Error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDormitory(@PathVariable Integer id) {
        try {
            dormitoryService.deleteDormitory(id);
            return ResponseEntity.noContent().build(); // No Content
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Internal Server Error
        }
    }
}
