package com.example.energyappjava.ModelController.Actors.Reads;

public abstract class Reads {
    private String date_range;
    private String measure_value;
    private String user_id;

    public Reads(String date_range, String measure_value, String user_id) {
        this.date_range = date_range;
        this.measure_value = measure_value;
        this.user_id = user_id;
    }

    public String getDateRange() {
        return date_range;
    }

    public String getTime() {
        return measure_value;
    }

    public String getValue() {
        return user_id;
    }

    public void setDateRange(String date) {
        this.date_range = date_range;
    }

    public void setTime(String measure_value) {
        this.measure_value = measure_value;
    }

    public void setValue(String user_id) {
        this.user_id = user_id;
    }

    public abstract String getConsume();
}