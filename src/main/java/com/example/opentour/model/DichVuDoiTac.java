package com.example.opentour.model;

import java.io.Serializable;

public class DichVuDoiTac implements Serializable {
    private int id;
    private String description;
    private DoiTac doiTac;
    private DichVu dichVu;

    public DichVuDoiTac() {
    }

    public DichVuDoiTac(int id, DichVu dichVu) {
        this.id = id;
        this.dichVu = dichVu;
    }

    public DichVuDoiTac(int id, String description, DoiTac doiTac, DichVu dichVu) {
        this.id = id;
        this.description = description;
        this.doiTac = doiTac;
        this.dichVu = dichVu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DoiTac getDoiTac() {
        return doiTac;
    }

    public void setDoiTac(DoiTac doiTac) {
        this.doiTac = doiTac;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }
}
