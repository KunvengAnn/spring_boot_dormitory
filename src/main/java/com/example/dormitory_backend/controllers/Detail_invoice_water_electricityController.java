package com.example.dormitory_backend.controllers;

import com.example.dormitory_backend.models.Detail_invoice_water_electricity;
import com.example.dormitory_backend.services.Detail_invoice_water_electricityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/detail-invoice-water-electricity")
public class Detail_invoice_water_electricityController {

    @Autowired
    private Detail_invoice_water_electricityService detailInvoiceWaterElectricityService;

    @GetMapping
    public ResponseEntity<List<Detail_invoice_water_electricity>> getAllDetails() {
        List<Detail_invoice_water_electricity> details = detailInvoiceWaterElectricityService.getAllDetail_invoice_water_electricity();
        return ResponseEntity.ok(details);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detail_invoice_water_electricity> getDetailById(@PathVariable Integer id) {
        Optional<Detail_invoice_water_electricity> detail = detailInvoiceWaterElectricityService.getDetail_invoice_water_electricityById(id);
        if (detail.isPresent()) {
            return ResponseEntity.ok(detail.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Detail_invoice_water_electricity> createDetail(@RequestBody Detail_invoice_water_electricity detail) {
        Detail_invoice_water_electricity savedDetail = detailInvoiceWaterElectricityService.save(detail);
        return ResponseEntity.status(201).body(savedDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Detail_invoice_water_electricity> updateDetail(
            @PathVariable int id,
            @RequestBody Detail_invoice_water_electricity updatedDetail) {
        try {
            Detail_invoice_water_electricity updated = detailInvoiceWaterElectricityService.updateDetail_invoice_water_electricity(id, updatedDetail);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetail(@PathVariable int id) {
        try {
            detailInvoiceWaterElectricityService.deleteDetail_invoice_water_electricityById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
