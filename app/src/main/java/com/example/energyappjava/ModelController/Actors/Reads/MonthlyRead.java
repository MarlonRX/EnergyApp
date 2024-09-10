package com.example.energyappjava.ModelController.Actors.Reads;

public class MonthlyRead extends Reads {
    public MonthlyRead(String date_range, String measure_value, String user_id) {
        super(date_range, measure_value, user_id);
    }

    @Override
    public String getConsume() {
        // Implement the logic for monthly consumption
        return "Monthly consumption logic";
    }
}