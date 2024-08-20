package com.example.energyappjava.ModelController.Actors;

public class Reads {
    private String date;
    private String time;
    private String value;

    public Reads(String date, String time, String value) {
        this.date = date;
        this.time = time;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getValue() {
        return value;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
