package com.example.opentour.model;

import java.io.Serializable;

public class DiaDiem implements Serializable {
    private int id;
    private String name;
    private String city;
    private String district;
    private String description;

    public DiaDiem() {
    }

    public DiaDiem(int id, String name, String city, String district, String description) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.district = district;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
