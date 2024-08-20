package com.example.energyappjava.ModelController;

import android.content.Context;

public class MeterController {

    private final Context context;

    public MeterController(Context context) {
        this.context = context;
    }

    public String fetchMeasurement() {
        // Fetch the measurement from the meter
        return "100";
    }

    public String getPreviousMeasurement() {
        return "ejemplo";
    }

    public void saveMeasurement(String measurement) {
        // Save the measurement to the meter
    }
}