package com.example.dormitory_backend.controllers;

import com.example.dormitory_backend.models.Dormitory;
import com.example.dormitory_backend.models.Invoice_water_electricity;
import com.example.dormitory_backend.services.Invoice_water_electricityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/Invoice_w_e")
public class Invoice_water_electricityController {
    @Autowired
    private Invoice_water_electricityService invoice_water_electricityService;

    @GetMapping
    public List<Invoice_water_electricity> getAll() {
        return invoice_water_electricityService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice_water_electricity> getById(@PathVariable int id) {
        Optional<Invoice_water_electricity> invoiceWaterElectricity = invoice_water_electricityService.getById(id);
        return invoiceWaterElectricity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Invoice_water_electricity createInvoiceWaterElectricity(@RequestBody Invoice_water_electricity invoiceWaterElectricity) {
        return invoice_water_electricityService.save(invoiceWaterElectricity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice_water_electricity> updateInvoiceWaterElectricity(@RequestBody Invoice_water_electricity invoiceWaterElectricity, @PathVariable Integer id) {
        Invoice_water_electricity updateInvoiceWaterElectricity = invoice_water_electricityService.updateInvoiceWaterElectricity(id, invoiceWaterElectricity);

        if (updateInvoiceWaterElectricity != null) {
            return ResponseEntity.ok(updateInvoiceWaterElectricity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Dormitory> deleteInvoiceWaterElectricity(@PathVariable Integer id) {
        invoice_water_electricityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
