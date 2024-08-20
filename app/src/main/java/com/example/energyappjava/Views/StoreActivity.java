// StoreActivity.java
package com.example.energyappjava.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import com.example.energyappjava.ModelController.Actors.EnergyMeter;
import com.example.energyappjava.ModelController.StoreController;
import com.example.energyappjava.R;

import java.util.List;

public class StoreActivity extends ComponentActivity {
    private StoreController storeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);

        TableLayout devicesTable = findViewById(R.id.devices_table);
        storeController = new StoreController(this, devicesTable);
        List<EnergyMeter> energyMeters = storeController.fetchEnergyMeters();

        Button IndexButton = findViewById(R.id.measure_button);
        Button ConsumeButton = findViewById(R.id.consume_button);


        IndexButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, IndexActivity.class);
            startActivity(intent);
        });

        ConsumeButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ConsumeActivity.class);
            startActivity(intent);
        });

        populateTable(devicesTable, energyMeters);
    }

    private void populateTable(TableLayout table, List<EnergyMeter> energyMeters) {
        // Clear existing rows
        table.removeAllViews();

        // Iterate over energy meters and add rows to the table
        for (EnergyMeter meter : energyMeters) {
            TableRow row = new TableRow(this);

            TextView nameView = new TextView(this);
            nameView.setText(meter.getName());
            row.addView(nameView);

            TextView typeView = new TextView(this);
            typeView.setText(meter.getType());
            row.addView(typeView);

            TextView statusView = new TextView(this);
            statusView.setText(meter.getStatus());
            row.addView(statusView);

            TextView measureView = new TextView(this);
            measureView.setText(meter.getMeasure());
            row.addView(measureView);

            TextView priceView = new TextView(this);
            priceView.setText(String.valueOf(meter.getPrice()));
            row.addView(priceView);

            table.addView(row);
        }
    }
}