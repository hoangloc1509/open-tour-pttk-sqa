package com.example.opentour.model;

import java.io.Serializable;

public class DichVuCungCap implements Serializable {
    private int id;
    private String description;
    private DichVuDoiTac dvDoiTac;
    private DiaDiem diaDiem;

    public DichVuCungCap() {
    }

    public DichVuCungCap(int id, DichVuDoiTac dvDoiTac) {
        this.id = id;
        this.dvDoiTac = dvDoiTac;
    }

    public DichVuCungCap(int id, String description, DichVuDoiTac dvDoiTac, DiaDiem diaDiem) {
        this.id = id;
        this.description = description;
        this.dvDoiTac = dvDoiTac;
        this.diaDiem = diaDiem;
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

    public DichVuDoiTac getDvDoiTac() {
        return dvDoiTac;
    }

    public void setDvDoiTac(DichVuDoiTac dvDoiTac) {
        this.dvDoiTac = dvDoiTac;
    }

    public DiaDiem getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(DiaDiem diaDiem) {
        this.diaDiem = diaDiem;
    }
}
