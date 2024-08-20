// StoreActivity.java
package com.example.energyappjava.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;

import androidx.activity.ComponentActivity;

import com.example.energyappjava.ModelController.StoreController;
import com.example.energyappjava.R;

public class StoreActivity extends ComponentActivity {
    private StoreController storeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);

        TableLayout devicesTable = findViewById(R.id.devices_table);
        storeController = new StoreController(this, devicesTable);
        storeController.fetchDevicesFromFirebase();

        Button ConsumeButton = findViewById(R.id.consume_button);
        Button MeasureButton = findViewById(R.id.measure_button);

        MeasureButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, IndexActivity.class);
            startActivity(intent);
        });

        ConsumeButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ConsumeActivity.class);
            startActivity(intent);
        });
    }
}