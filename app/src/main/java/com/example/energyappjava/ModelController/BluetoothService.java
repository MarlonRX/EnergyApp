package com.example.energyappjava.ModelController;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class BluetoothService {
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private final BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;
    private InputStream inputStream;
    private final Context context;

    public BluetoothService(Context context) {
        this.context = context;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public boolean connect(String deviceAddress) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(deviceAddress);
        try {
            bluetoothSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
            bluetoothSocket.connect();
            inputStream = bluetoothSocket.getInputStream();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String readData() throws IOException {
        byte[] buffer = new byte[1024];
        int bytes;
        StringBuilder data = new StringBuilder();
        while ((bytes = inputStream.read(buffer)) != -1) {
            data.append(new String(buffer, 0, bytes));
        }
        return data.toString();
    }

    public void close() throws IOException {
        if (inputStream != null) {
            inputStream.close();
        }
        if (bluetoothSocket != null) {
            bluetoothSocket.close();
        }
    }
}