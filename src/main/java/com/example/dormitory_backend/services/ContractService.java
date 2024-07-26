package com.example.dormitory_backend.services;

import com.example.dormitory_backend.models.Contract;
import com.example.dormitory_backend.models.Dormitory;
import com.example.dormitory_backend.models.Room;
import com.example.dormitory_backend.models.Student;
import com.example.dormitory_backend.repositories.ContractRepository;
import com.example.dormitory_backend.utils.ContractDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private DormitoryService dormitoryService;

    @Autowired
    private RoomService roomService;


    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Optional<Contract> getContractById(Integer id) {
        return contractRepository.findById(id);
    }

    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public Contract updateContract(Integer id, ContractDTO contractDTO) {
        Optional<Contract> existingContractOpt = contractRepository.findById(id);
        if (existingContractOpt.isEmpty()) {
            throw new IllegalArgumentException("Contract not found.");
        }

        Contract existingContract = existingContractOpt.get();

        // Update IDs if they are provided
        if (contractDTO.getStudentId() != null) {
            Optional<Student> student = studentService.getStudentById_no_token(contractDTO.getStudentId());
            if (student.isPresent()) {
                existingContract.setStudent(student.get());
            } else {
                throw new IllegalArgumentException("Student not found.");
            }
        }
        if (contractDTO.getDormitoryId() != null) {
            Optional<Dormitory> dormitory = dormitoryService.getDormitoryById(contractDTO.getDormitoryId());
            if (dormitory.isPresent()) {
                existingContract.setDormitory(dormitory.get());
            } else {
                throw new IllegalArgumentException("Dormitory not found.");
            }
        }
        if (contractDTO.getRoomId() != null) {
            Optional<Room> room = roomService.getRoomById(contractDTO.getRoomId());
            if (room.isPresent()) {
                existingContract.setRoom(room.get());
            } else {
                throw new IllegalArgumentException("Room not found.");
            }
        }

        // Update contract dates if they are provided
        if (contractDTO.getDate_start_contract() != null) {
            existingContract.setDate_start_contract(contractDTO.getDate_start_contract());
        }
        if (contractDTO.getDate_end_contract() != null) {
            existingContract.setDate_end_contract(contractDTO.getDate_end_contract());
        }

        return contractRepository.save(existingContract);
    }

    public void deleteContract(Integer id) {
        Optional<Contract> existingContract = contractRepository.findById(id);
        if (existingContract.isPresent()) {
            contractRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Contract with ID " + id + " not found.");
        }
    }

}
