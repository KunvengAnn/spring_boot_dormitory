package com.example.dormitory_backend.services;

import com.example.dormitory_backend.models.Invoice_water_electricity;
import com.example.dormitory_backend.repositories.Invoice_water_electricityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Invoice_water_electricityService {

    @Autowired
    private Invoice_water_electricityRepository invoice_water_electricityRepository;

    public List<Invoice_water_electricity> getAll() {
        return invoice_water_electricityRepository.findAll();
    }

    public Optional<Invoice_water_electricity> getById(int id) {
        return invoice_water_electricityRepository.findById(id);
    }

    public Invoice_water_electricity save(Invoice_water_electricity invoice_water_electricity) {
        return invoice_water_electricityRepository.save(invoice_water_electricity);
    }

    public Invoice_water_electricity updateInvoiceWaterElectricity(int id, Invoice_water_electricity invoiceDetails) {
        Optional<Invoice_water_electricity> optionalInvoice = invoice_water_electricityRepository.findById(id);
        if (optionalInvoice.isPresent()) {
            Invoice_water_electricity existingInvoice = optionalInvoice.get();
            existingInvoice.setTotal_amount_water_electricity(invoiceDetails.getTotal_amount_water_electricity());
            existingInvoice.setCreated_at(invoiceDetails.getCreated_at());
            existingInvoice.setRoom(invoiceDetails.getRoom());
            existingInvoice.setDetailInvoiceWaterElectricities(invoiceDetails.getDetailInvoiceWaterElectricities());

            return invoice_water_electricityRepository.save(existingInvoice);
        } else {
            return null; // Or throw an exception
        }
    }

    public void delete(Integer id) {
        invoice_water_electricityRepository.deleteById(id);
    }

}
