package com.example.dormitory_backend.controllers;

import com.example.dormitory_backend.models.Water_electricity;
import com.example.dormitory_backend.services.Water_electricityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/water-electricity")
public class Water_electricityController {

    @Autowired
    private Water_electricityService waterElectricityService;

    @GetMapping
    public ResponseEntity<List<Water_electricity>> getAllWaterElectricities() {
        List<Water_electricity> waterElectricities = waterElectricityService.getAllWater_electricity();
        return ResponseEntity.ok(waterElectricities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Water_electricity> getWaterElectricityById(@PathVariable Integer id) {
        Optional<Water_electricity> waterElectricity = waterElectricityService.getWater_electricityById(id);
        if (waterElectricity.isPresent()) {
            return ResponseEntity.ok(waterElectricity.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Water_electricity> createWaterElectricity(@RequestBody Water_electricity waterElectricity) {
        Water_electricity savedWaterElectricity = waterElectricityService.saveWater_electricity(waterElectricity);
        return ResponseEntity.status(201).body(savedWaterElectricity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Water_electricity> updateWaterElectricity(
            @PathVariable Integer id,
            @RequestBody Water_electricity waterDetails) {
        try {
            Water_electricity updatedWaterElectricity = waterElectricityService.updateWater_electricity(id, waterDetails);
            return ResponseEntity.ok(updatedWaterElectricity);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWaterElectricity(@PathVariable Integer id) {
        try {
            waterElectricityService.deleteWaterElectricity(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
