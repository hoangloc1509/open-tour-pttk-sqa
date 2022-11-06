package com.example.opentour.model;

import java.io.Serializable;

public class DichVuDaSuDung implements Serializable {
    private int id;
    private int quantity;
    private Double amount;
    private String startDate;
    private DichVuCungCap dvCungCap;

    public DichVuDaSuDung() {
    }

    public DichVuDaSuDung(int id, int quantity, Double amount, String startDate, DichVuCungCap dvCungCap) {
        this.id = id;
        this.quantity = quantity;
        this.amount = amount;
        this.startDate = startDate;
        this.dvCungCap = dvCungCap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public DichVuCungCap getDvCungCap() {
        return dvCungCap;
    }

    public void setDvCungCap(DichVuCungCap dvCungCap) {
        this.dvCungCap = dvCungCap;
    }
}
