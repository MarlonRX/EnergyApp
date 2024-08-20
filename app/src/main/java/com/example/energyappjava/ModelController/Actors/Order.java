package com.example.energyappjava.ModelController.Actors;

import java.util.List;

public class Order {
    private User user;
    private List<EnergyMeter> products;
    private String value;
    private String detail;

    public Order(User user, List<EnergyMeter> products, String value, String detail) {
        this.user = user;
        this.products = products;
        this.value = value;
        this.detail = detail;
    }

    public User getUser() {
        return user;
    }

    public List<EnergyMeter> getProducts() {
        return products;
    }

    public String getValue() {
        return value;
    }

    public String getDetail() {
        return detail;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProducts(List<EnergyMeter> products) {
        this.products = products;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
