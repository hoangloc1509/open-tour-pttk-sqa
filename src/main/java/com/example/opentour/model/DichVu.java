package com.example.opentour.model;

import java.io.Serializable;

public class DichVu implements Serializable {
    private int id;
    private String name;
    private Double unitPrice;
    private String description;

    public DichVu() {
    }

    public DichVu(int id, String name, Double unitPrice) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public DichVu(int id, String name, Double unitPrice, String description) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
