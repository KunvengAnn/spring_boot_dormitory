package com.example.dormitory_backend.services;

import com.example.dormitory_backend.models.Equipment;
import com.example.dormitory_backend.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Equipment> getAll() {
        return equipmentRepository.findAll();
    }

    public Optional<Equipment> getById(int id) {
        return equipmentRepository.findById(id);
    }

    public Equipment save(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(int id, Equipment equipmentDetails) {
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(id);
        if (optionalEquipment.isPresent()) {
            Equipment existingEquipment = optionalEquipment.get();
            existingEquipment.setEquipment_name(equipmentDetails.getEquipment_name());
            existingEquipment.setEquipment_type(equipmentDetails.getEquipment_type());
            existingEquipment.setRoomEquipments(equipmentDetails.getRoomEquipments());

            return equipmentRepository.save(existingEquipment);
        } else {
            return null; // Or throw an exception
        }
    }

    public void deleteById(int id) {
        equipmentRepository.deleteById(id);
    }
}
