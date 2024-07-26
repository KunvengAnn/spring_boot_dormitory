package com.example.dormitory_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoice_water_electricitys")
public class Invoice_water_electricity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_invoice_w_e;

    @ManyToOne
    @JoinColumn(name = "id_room", nullable = false)
    @JsonIgnoreProperties({"invoiceWaterElectricities", "contracts", "dormitory"})
    private Room room;

    private Double total_amount_water_electricity;
    private LocalDate created_at;

    @OneToMany(mappedBy = "invoiceWaterElectricity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("invoiceWaterElectricity")
    private List<Detail_invoice_water_electricity> detailInvoiceWaterElectricities;

    ////
    public Integer getId_invoice_w_e() {
        return id_invoice_w_e;
    }

    public void setId_invoice_w_e(Integer id_invoice_w_e) {
        this.id_invoice_w_e = id_invoice_w_e;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getTotal_amount_water_electricity() {
        return total_amount_water_electricity;
    }

    public void setTotal_amount_water_electricity(double total_amount_water_electricity) {
        this.total_amount_water_electricity = total_amount_water_electricity;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public List<Detail_invoice_water_electricity> getDetailInvoiceWaterElectricities() {
        return detailInvoiceWaterElectricities;
    }

    public void setDetailInvoiceWaterElectricities(List<Detail_invoice_water_electricity> detailInvoiceWaterElectricities) {
        this.detailInvoiceWaterElectricities = detailInvoiceWaterElectricities;
    }
}
