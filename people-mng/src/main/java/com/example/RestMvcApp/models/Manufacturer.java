package com.example.RestMvcApp.models;

import jakarta.persistence.*;

@Entity
public class Manufacturer {
    @Id
    @Column(name = "manufacturer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int postcode;
    private String phone;
    public Manufacturer()  {}

    public Manufacturer(int id, String name, int postcode, String phone) {
        this.id = id;
        this.name = name;
        this.postcode = postcode;
        this.phone = phone;
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

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", postcode=" + postcode +
                ", phone='" + phone + '\'' +
                '}' + "\n";
    }
}
