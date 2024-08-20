// Device.java
package com.example.energyappjava.ModelController.Actors;

public class EnergyMeter {
    private String name;
    private String type;
    private String status;
    private String consume;

    // Default constructor required for calls to DataSnapshot.getValue(Device.class)
    public EnergyMeter() {
    }

    public EnergyMeter(String name, String type, String status, String consume) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.consume = consume;
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

    public String getConsume() {
        return consume;
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

    public void setConsume(String consume) {
        this.consume = consume;
    }
}