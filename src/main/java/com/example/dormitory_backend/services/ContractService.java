package com.example.dormitory_backend.services;

import com.example.dormitory_backend.models.Contract;
import com.example.dormitory_backend.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Optional<Contract> getContractById(Integer id) {
        return contractRepository.findById(id);
    }

    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public void deleteContract(Integer id) {
        contractRepository.deleteById(id);
    }
}
