package com.example.opentour.model;

import java.io.Serializable;

public class ThanhVien implements Serializable {
    private int id;
    private String name;
    private String dob;
    private String gender;
    private String email;
    private String tel;

    public ThanhVien() {
    }

    public ThanhVien(int id, String name, String dob, String gender, String email, String tel) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.tel = tel;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
