package com.example.dormitory_backend.repositories;

import com.example.dormitory_backend.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Integer> {

}
