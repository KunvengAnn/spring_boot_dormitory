package com.example.dormitory_backend.services;

import com.example.dormitory_backend.models.Detail_invoice_water_electricity;
import com.example.dormitory_backend.repositories.Detail_invoice_water_electricityRepository;
import com.example.dormitory_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Detail_invoice_water_electricityService {
    @Autowired
    private Detail_invoice_water_electricityRepository detail_invoice_water_electricityRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Detail_invoice_water_electricity> getAllDetail_invoice_water_electricity() {
        return detail_invoice_water_electricityRepository.findAll();
    }

    public Optional<Detail_invoice_water_electricity> getDetail_invoice_water_electricityById(int id) {
        return detail_invoice_water_electricityRepository.findById(id);
    }

    public Detail_invoice_water_electricity save(Detail_invoice_water_electricity e) {
        return detail_invoice_water_electricityRepository.save(e);
    }

    public Detail_invoice_water_electricity updateDetail_invoice_water_electricity(int id, Detail_invoice_water_electricity details) {
        Optional<Detail_invoice_water_electricity> optionalDetail = detail_invoice_water_electricityRepository.findById(id);
        if (optionalDetail.isPresent()) {
            Detail_invoice_water_electricity existingDetail = optionalDetail.get();
            existingDetail.setWater_price(details.getWater_price());
            existingDetail.setElectricity_price(details.getElectricity_price());
            existingDetail.setTotal_water_price(details.getTotal_water_price());
            existingDetail.setTotal_electricity_price(details.getTotal_electricity_price());
            existingDetail.setInvoiceWaterElectricity(details.getInvoiceWaterElectricity());

            return detail_invoice_water_electricityRepository.save(existingDetail);
        } else {
            throw new RuntimeException("Detail_invoice_water_electricity not found with id: " + id);
        }
    }


    public void deleteDetail_invoice_water_electricityById(int id) {
        detail_invoice_water_electricityRepository.deleteById(id);
    }
}
