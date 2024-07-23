package com.example.dormitory_backend.repositories;

import com.example.dormitory_backend.models.Dormitory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DormitoryRepository extends JpaRepository<Dormitory, Integer> {

}
