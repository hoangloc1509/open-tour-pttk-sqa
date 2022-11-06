package com.example.opentour.model;

import java.io.Serializable;

public class NhanVien extends ThanhVien implements Serializable {
    private int id;
    private String username;
    private String password;
    private String position;

    public NhanVien() {
    }

    public NhanVien(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public NhanVien(int id, String username, String password, String position) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.position = position;
    }

    public NhanVien(int id, String name, String dob, String gender, String email, String tel, int id1, String username, String password, String position) {
        super(id, name, dob, gender, email, tel);
        this.id = id1;
        this.username = username;
        this.password = password;
        this.position = position;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
