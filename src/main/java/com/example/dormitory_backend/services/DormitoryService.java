package com.example.dormitory_backend.services;

import com.example.dormitory_backend.models.Dormitory;
import com.example.dormitory_backend.models.Dormitory;
import com.example.dormitory_backend.repositories.DormitoryRepository;
import com.example.dormitory_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DormitoryService {

    @Autowired
    private DormitoryRepository dormitoryRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Dormitory> getAllDormitory() {
        return dormitoryRepository.findAll();
    }

    public Optional<Dormitory> getDormitoryById(int id) {
        return dormitoryRepository.findById(id);
    }

    public Dormitory saveDormitory(Dormitory dormitory) {
        return dormitoryRepository.save(dormitory);
    }

    public Dormitory updateDormitory(int id, Dormitory dormitoryDetails) {
        Optional<Dormitory> optionalDormitory = dormitoryRepository.findById(id);
        if (optionalDormitory.isPresent()) {
            Dormitory existingDormitory = optionalDormitory.get();
            existingDormitory.setDormitory_name(dormitoryDetails.getDormitory_name());
            existingDormitory.setRooms(dormitoryDetails.getRooms());

            return dormitoryRepository.save(existingDormitory);
        } else {
            return null; // Or throw an exception
        }
    }

    public void deleteDormitory(int id) {
        dormitoryRepository.deleteById(id);
    }
}
