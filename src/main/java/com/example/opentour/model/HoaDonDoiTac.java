package com.example.opentour.model;

import java.io.Serializable;
import java.util.List;

public class HoaDonDoiTac implements Serializable {
    private int id;
    private String name;
    private String month;
    private String status;
    private Double totalAmount;
    private DoiTac doiTac;
    private NhanVien nhanVien;
    private List<DichVuDaSuDung> listDvDaSuDung;

    public HoaDonDoiTac() {
    }

    public HoaDonDoiTac(int id, String name, String month, String status, Double totalAmount, List<DichVuDaSuDung> listDvDaSuDung) {
        this.id = id;
        this.name = name;
        this.month = month;
        this.status = status;
        this.totalAmount = totalAmount;
        this.listDvDaSuDung = listDvDaSuDung;
    }

    public HoaDonDoiTac(int id, String name, String month, String status, Double totalAmount, DoiTac doiTac, NhanVien nhanVien, List<DichVuDaSuDung> listDvDaSuDung) {
        this.id = id;
        this.name = name;
        this.month = month;
        this.status = status;
        this.totalAmount = totalAmount;
        this.doiTac = doiTac;
        this.nhanVien = nhanVien;
        this.listDvDaSuDung = listDvDaSuDung;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public DoiTac getDoiTac() {
        return doiTac;
    }

    public void setDoiTac(DoiTac doiTac) {
        this.doiTac = doiTac;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public List<DichVuDaSuDung> getListDvDaSuDung() {
        return listDvDaSuDung;
    }

    public void setListDvDaSuDung(List<DichVuDaSuDung> listDvDaSuDung) {
        this.listDvDaSuDung = listDvDaSuDung;
    }
}
