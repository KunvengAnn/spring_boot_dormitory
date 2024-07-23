package com.example.dormitory_backend.services;

import com.example.dormitory_backend.models.RoomEquipment;
import com.example.dormitory_backend.repositories.RoomEquipmentRepository;
import com.example.dormitory_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomEquipmentService {

    @Autowired
    private RoomEquipmentRepository roomEquipmentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<RoomEquipment> findAll() {
        return roomEquipmentRepository.findAll();
    }

    public Optional<RoomEquipment> findById(int id) {
        return roomEquipmentRepository.findById(id);
    }

    public RoomEquipment save(RoomEquipment roomEquipment) {
        return roomEquipmentRepository.save(roomEquipment);
    }

    // Update an existing RoomEquipment by ID
    public RoomEquipment update(int id, RoomEquipment updatedRoomEquipment) {
        Optional<RoomEquipment> optionalRoomEquipment = roomEquipmentRepository.findById(id);
        if (optionalRoomEquipment.isPresent()) {
            RoomEquipment existingRoomEquipment = optionalRoomEquipment.get();

            // Update the fields
            existingRoomEquipment.setEquipment(updatedRoomEquipment.getEquipment());
            existingRoomEquipment.setRoom(updatedRoomEquipment.getRoom());
            existingRoomEquipment.setQuantity_equipment(updatedRoomEquipment.getQuantity_equipment());
            existingRoomEquipment.setStatus(updatedRoomEquipment.getStatus());

            // Save the updated record
            return roomEquipmentRepository.save(existingRoomEquipment);
        } else {
            throw new RuntimeException("RoomEquipment not found with id: " + id);
        }
    }


    public void delete(Integer id) {
        roomEquipmentRepository.deleteById(id);
    }
}
