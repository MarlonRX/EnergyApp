// Device.java
package com.example.energyappjava.ModelController.Actors;

public class EnergyMeter {
    private String name;
    private String type;
    private String status;
    private String measure;
    private double price;

    public EnergyMeter(String name, String type, String status, String measure, double price) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.measure = measure;
        this.price = price;
    }

    //constructor for store
    public EnergyMeter(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.measure = measure;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getMeasure() {
        return measure;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}