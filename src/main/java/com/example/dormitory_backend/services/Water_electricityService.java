package com.example.dormitory_backend.services;

import com.example.dormitory_backend.models.Water_electricity;
import com.example.dormitory_backend.repositories.Water_electricityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Water_electricityService {

    @Autowired
    private Water_electricityRepository water_electricityRepository;

    public List<Water_electricity> getAllWater_electricity() {
        return water_electricityRepository.findAll();
    }

    public Optional<Water_electricity> getWater_electricityById(int id) {
        return water_electricityRepository.findById(id);
    }

    public Water_electricity saveWater_electricity(Water_electricity water_electricity) {
        return water_electricityRepository.save(water_electricity);
    }

    public Water_electricity updateWater_electricity(int id, Water_electricity waterDetails) {
        Optional<Water_electricity> optionalWaterElectricity = water_electricityRepository.findById(id);
        if (optionalWaterElectricity.isPresent()) {
            Water_electricity existingWaterElectricity = optionalWaterElectricity.get();
            existingWaterElectricity.setQuantity_electricity_use_start(waterDetails.getQuantity_electricity_use_start());
            existingWaterElectricity.setQuantity_electricity_use_end(waterDetails.getQuantity_electricity_use_end());
            existingWaterElectricity.setQuantity_water_use_start(waterDetails.getQuantity_water_use_start());
            existingWaterElectricity.setQuantity_water_use_end(waterDetails.getQuantity_water_use_end());
            existingWaterElectricity.setRoom(waterDetails.getRoom());

            return water_electricityRepository.save(existingWaterElectricity);
        } else {
            throw new RuntimeException("Water_electricity not found with id: " + id);
        }
    }

    public void deleteWaterElectricity(int id) {
        if (water_electricityRepository.existsById(id)) {
            water_electricityRepository.deleteById(id);
        } else {
            throw new RuntimeException("Water_electricity not found with id: " + id);
        }
    }
}
