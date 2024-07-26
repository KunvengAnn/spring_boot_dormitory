package com.example.dormitory_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "detail_invoice_water_electricitys")
public class Detail_invoice_water_electricity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_dt_invoice_w;

    @ManyToOne
    @JoinColumn(name = "id_invoice_w_e", nullable = false)
    @JsonIgnoreProperties({"detailInvoiceWaterElectricities", "room"})
    private Invoice_water_electricity invoiceWaterElectricity;

    private Double water_price;
    private Double electricity_price;
    private Double total_water_price;
    private Double total_electricity_price;

    ////
    public Integer getId_dt_invoice_w() {
        return id_dt_invoice_w;
    }

    public void setId_dt_invoice_w(Integer id_dt_invoice_w) {
        this.id_dt_invoice_w = id_dt_invoice_w;
    }

    public Invoice_water_electricity getInvoiceWaterElectricity() {
        return invoiceWaterElectricity;
    }

    public void setInvoiceWaterElectricity(Invoice_water_electricity invoiceWaterElectricity) {
        this.invoiceWaterElectricity = invoiceWaterElectricity;
    }

    public Double getWater_price() {
        return water_price;
    }

    public void setWater_price(Double water_price) {
        this.water_price = water_price;
    }

    public Double getElectricity_price() {
        return electricity_price;
    }

    public void setElectricity_price(Double electricity_price) {
        this.electricity_price = electricity_price;
    }

    public Double getTotal_water_price() {
        return total_water_price;
    }

    public void setTotal_water_price(Double total_water_price) {
        this.total_water_price = total_water_price;
    }

    public Double getTotal_electricity_price() {
        return total_electricity_price;
    }

    public void setTotal_electricity_price(Double total_electricity_price) {
        this.total_electricity_price = total_electricity_price;
    }
}
