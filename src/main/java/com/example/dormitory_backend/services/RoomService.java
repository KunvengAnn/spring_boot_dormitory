package com.example.dormitory_backend.services;

import com.example.dormitory_backend.models.Room;
import com.example.dormitory_backend.repositories.RoomRepository;
import com.example.dormitory_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(int id) {
        return roomRepository.findById(id);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(int id, Room roomDetails) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            Room existingRoom = optionalRoom.get();
            existingRoom.setRoom_number(roomDetails.getRoom_number());
            existingRoom.setRoom_price(roomDetails.getRoom_price());
            existingRoom.setRoomEquipments(roomDetails.getRoomEquipments());
            existingRoom.setDormitory(roomDetails.getDormitory());
            existingRoom.setContracts(roomDetails.getContracts());
            existingRoom.setInvoiceWaterElectricities(roomDetails.getInvoiceWaterElectricities());
            existingRoom.setWaterElectricities(roomDetails.getWaterElectricities());
            existingRoom.setRoom_max_capacity(roomDetails.getRoom_max_capacity());
            existingRoom.setQuantity_person(roomDetails.getQuantity_person());
            existingRoom.setStatus_is_empty_room(roomDetails.isStatus_is_empty_room());
            existingRoom.setStatus_room_is_boy(roomDetails.isStatus_room_is_boy());

            return roomRepository.save(existingRoom);
        } else {
            return null; // Or throw an exception
        }
    }

    public void deleteRoomById(int id) {
        roomRepository.deleteById(id);
    }
}
