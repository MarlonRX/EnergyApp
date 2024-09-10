package com.example.energyappjava.ModelController;

import android.content.Context;
import java.io.IOException;

public class MeterController {
    private final Context context;
    private final BluetoothService bluetoothService;
    private final String deviceAddress;

    public MeterController(Context context, String deviceAddress) {
        this.context = context;
        this.deviceAddress = deviceAddress;
        this.bluetoothService = new BluetoothService(context);
    }

    public String fetchMeasurement() {
        if (bluetoothService.connect(deviceAddress)) {
            try {
                String data = bluetoothService.readData();
                bluetoothService.close();
                return data;
            } catch (IOException e) {
                e.printStackTrace();
                return "Error consiguiendo lecturas";
            }
        } else {
            return "Conexión fallida";
        }
    }

    public String getPreviousMeasurement() {
        return "ejemplo";
    }

    public void saveMeasurement(String measurement) {
        // Save measurement to database
    }
}