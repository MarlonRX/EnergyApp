package com.example.energyappjava.ModelController.Actors.Reads;

public class HourlyRead extends Reads {
    public HourlyRead(String date_range, String measure_value, String user_id) {
        super(date_range, measure_value, user_id);
    }

    @Override
    public String getConsume() {
        // Implement the logic for hourly consumption
        return "Hourly consumption logic";
    }
}