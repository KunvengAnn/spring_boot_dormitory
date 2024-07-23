package com.example.dormitory_backend.repositories;

import com.example.dormitory_backend.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

}
