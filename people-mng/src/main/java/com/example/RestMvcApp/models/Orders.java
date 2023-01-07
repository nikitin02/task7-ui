package com.example.RestMvcApp.models;

import jakarta.persistence.*;

@Entity
public class Orders {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String payment;
    private String created_at;
    private int person_id;
    private int delivery_id;
    private String status;

    public Orders() {}

    public Orders(int id, String payment, String created_at, int person_id, int delivery_id, String status) {
        this.id = id;
        this.payment = payment;
        this.created_at = created_at;
        this.person_id = person_id;
        this.delivery_id = delivery_id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(int delivery_id) {
        this.delivery_id = delivery_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", payment='" + payment + '\'' +
                ", created_at='" + created_at + '\'' +
                ", person_id=" + person_id +
                ", delivery_id=" + delivery_id +
                ", status='" + status + '\'' +
                '}' + "\n";
    }

}
