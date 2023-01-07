package com.example.RestMvcApp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PersonDTO {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Size(min = 1, max = 100)
    @Pattern(regexp = "[A-Za-z]\\w+", message = "Incorrect first name")
    private String first_name;


    @Size(min = 1, max = 100)
    @Pattern(regexp = "[A-Za-z]\\w+", message = "Incorrect last name")
    private String last_name;


    @Size(min = 4, max = 16)
    private String password;


    @Size(min = 8, max = 16)
    @Pattern(regexp = "([+]\\d+)|(\\d+)", message = "Incorrect phone number")
    private String phone;


    @Email(message = "Incorrect email")
    @NotEmpty
    @Pattern(regexp = "\\w+")
    private String email;


    @NotEmpty
    private String location;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
