package com.example.energyappjava.ModelController.Actors;

import java.util.List;

public class Order {
    private User user;
    private List<EnergyMeter> products;
    private Double value;
    private String detail;
    private String address;
    private String customer_phone;

    public Order(User user, List<EnergyMeter> products, Double value, String detail, String address, String customer_phone) {
        this.user = user;
        this.products = products;
        this.value = value;
        this.detail = detail;
        this.address = address;
        this.customer_phone = customer_phone;
    }

    public User getUser() {
        return user;
    }

    public List<EnergyMeter> getProducts() {
        return products;
    }

    public Double getValue() {
        return value;
    }

    public String getDetail() {
        return detail;
    }

    public String getAddress() {
        return address;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProducts(List<EnergyMeter> products) {
        this.products = products;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }
}
