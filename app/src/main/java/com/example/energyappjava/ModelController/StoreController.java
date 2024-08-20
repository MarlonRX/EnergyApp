// StoreController.java
package com.example.energyappjava.ModelController;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import com.example.energyappjava.ModelController.Actors.EnergyMeter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoreController {
    private final ComponentActivity activity;
    private final TableLayout devicesTable;

    public StoreController(ComponentActivity activity, TableLayout devicesTable) {
        this.activity = activity;
        this.devicesTable = devicesTable;
    }

    public void fetchDevicesFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Devices");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot deviceSnapshot : dataSnapshot.getChildren()) {
                    EnergyMeter device = deviceSnapshot.getValue(EnergyMeter.class);
                    if (device != null) {
                        addDeviceToTable(device);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void addDeviceToTable(EnergyMeter device) {
        TableRow row = new TableRow(activity);
        TextView nameTextView = new TextView(activity);
        TextView typeTextView = new TextView(activity);
        TextView statusTextView = new TextView(activity);
        TextView consumeTextView = new TextView(activity);

        nameTextView.setText(device.getName());
        typeTextView.setText(device.getType());
        statusTextView.setText(device.getStatus());
        consumeTextView.setText(device.getConsume());

        nameTextView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        typeTextView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        statusTextView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        consumeTextView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));

        nameTextView.setGravity(android.view.Gravity.CENTER);
        typeTextView.setGravity(android.view.Gravity.CENTER);
        statusTextView.setGravity(android.view.Gravity.CENTER);
        consumeTextView.setGravity(android.view.Gravity.CENTER);

        row.addView(nameTextView);
        row.addView(typeTextView);
        row.addView(statusTextView);
        row.addView(consumeTextView);

        devicesTable.addView(row);
    }
}