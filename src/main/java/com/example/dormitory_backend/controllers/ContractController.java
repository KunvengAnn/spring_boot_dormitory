package com.example.dormitory_backend.controllers;

import com.example.dormitory_backend.models.Contract;
import com.example.dormitory_backend.models.Student;
import com.example.dormitory_backend.models.Dormitory;
import com.example.dormitory_backend.models.Room;
import com.example.dormitory_backend.services.ContractService;
import com.example.dormitory_backend.services.StudentService;
import com.example.dormitory_backend.services.DormitoryService;
import com.example.dormitory_backend.services.RoomService;
import com.example.dormitory_backend.utils.ContractDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts() {
        try {
            List<Contract> contracts = contractService.getAllContracts();
            return ResponseEntity.ok(contracts);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable Integer id) {
        try {
            Optional<Contract> contract = contractService.getContractById(id);
            return contract.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<Contract> createContract(@RequestBody ContractDTO contractDTO) {
        try {
            // Validate IDs
            if (contractDTO.getStudentId() == null || contractDTO.getDormitoryId() == null || contractDTO.getRoomId() == null) {
                return ResponseEntity.badRequest().build();
            }

            // Fetch full entities
            Optional<Student> student = studentService.getStudentById_no_token(contractDTO.getStudentId());
            Optional<Dormitory> dormitory = dormitoryService.getDormitoryById(contractDTO.getDormitoryId());
            Optional<Room> room = roomService.getRoomById(contractDTO.getRoomId());

            if (student.isEmpty() || dormitory.isEmpty() || room.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            // Create and save contract
            Contract contract = new Contract();
            contract.setStudent(student.get());
            contract.setDormitory(dormitory.get());
            contract.setRoom(room.get());
            contract.setDate_start_contract(contractDTO.getDate_start_contract());
            contract.setDate_end_contract(contractDTO.getDate_end_contract());

            Contract savedContract = contractService.saveContract(contract);
            return ResponseEntity.status(201).body(savedContract);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable Integer id, @RequestBody Contract contractDetails) {
        try {
            Optional<Contract> existingContract = contractService.getContractById(id);
            if (existingContract.isPresent()) {
                Contract updatedContract = existingContract.get();
                updatedContract.setStudent(contractDetails.getStudent());
                updatedContract.setDormitory(contractDetails.getDormitory());
                updatedContract.setRoom(contractDetails.getRoom());
                updatedContract.setDate_start_contract(contractDetails.getDate_start_contract());
                updatedContract.setDate_end_contract(contractDetails.getDate_end_contract());
                return ResponseEntity.ok(contractService.saveContract(updatedContract));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Integer id) {
        try {
            contractService.deleteContract(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
