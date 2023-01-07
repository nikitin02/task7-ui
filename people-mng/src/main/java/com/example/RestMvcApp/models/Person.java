package com.example.RestMvcApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Person")
public class Person {
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


    @Email
    @NotEmpty
    private String email;


    @NotEmpty
    private String location;

    public Person() {
    }


    public Person(String first_name, String last_name, String password, String phone, String email, String location) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", first_name= '" + first_name + '\'' +
                ", last_name= '" + last_name + '\'' +
                ", password= '" + password + '\'' +
                ", phone= '" + phone + '\'' +
                ", email= '" + email + '\'' +
                ", location= '" + location + '\'' +
                '}' + "\n";
    }
}
