package com.example.energyappjava.ModelController;

public class CosumeController {
    private final MeterController meterController;

    public CosumeController(MeterController meterController) {
        this.meterController = meterController;
    }

    public String fetchMeasurement() {
        return meterController.fetchMeasurement();
    }

    public String getPreviousMeasurement() {
        return meterController.getPreviousMeasurement();
    }

    public void saveMeasurement(String measurement) {
        meterController.saveMeasurement(measurement);
    }
}
