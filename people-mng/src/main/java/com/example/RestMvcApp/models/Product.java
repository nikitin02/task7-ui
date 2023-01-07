package com.example.RestMvcApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    @Size(min = 2, max = 50)
    @Pattern(regexp = "[A-Za-z]\\w+", message = "Incorrect name")
    private String name;
    @Min(1)
    private int price;
    @Min(0)
    private int amount;

    private int category_id;

    private int manufacturer_id;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    ProductStatus productStatus;

    public ProductStatus getStatus() {
        return productStatus;
    }

    public void setStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product() {
    }

    public Product(String name, int price, int amount, int category_id, int manufacturer_id, ProductStatus productStatus) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.productStatus = productStatus;
        this.category_id = category_id;
        this.manufacturer_id = manufacturer_id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

//    public String getStatus() {
//        return status;
//    }

//    public void setStatus(String status) {
//        this.status = status;
//    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", " + category +
                ", manufacturer_id=" + manufacturer_id +
                ", status='" + productStatus + '\'' +
                '}' + "\n";
    }
}
