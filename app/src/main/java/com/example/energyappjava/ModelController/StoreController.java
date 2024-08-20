// StoreController.java
package com.example.energyappjava.ModelController;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import com.example.energyappjava.ModelController.Actors.EnergyMeter;
import com.example.energyappjava.ModelController.Actors.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StoreController {
    private final ComponentActivity activity;
    private final TableLayout devicesTable;

    public StoreController(ComponentActivity activity, TableLayout devicesTable) {
        this.activity = activity;
        this.devicesTable = devicesTable;
    }

    public List<EnergyMeter> fetchEnergyMeters() {
        List<EnergyMeter> energyMeters = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Devices");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataSnapshot typeSnapshot = snapshot.child("Type012");
                    if (typeSnapshot.exists()) {
                        String name = typeSnapshot.child("Name").getValue(String.class);
                        String type = typeSnapshot.child("Type").getValue(String.class);
                        String status = typeSnapshot.child("Status").getValue(String.class);
                        String measure = typeSnapshot.child("Measure").getValue(String.class);
                        Double price = Double.valueOf(typeSnapshot.child("Price").getValue(String.class).replace(",", ""));

                        EnergyMeter energyMeter = new EnergyMeter(name, type, status, measure, price);
                        energyMeters.add(energyMeter);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return energyMeters;
    }

    public void createOrder(Order order) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Orders");
        databaseReference.push().setValue(order);
    }

    public void cancelOrder(int orderId, UUID userId) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Orders");
        databaseReference.child(String.valueOf(orderId)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Order existingOrder = dataSnapshot.getValue(Order.class);
                if (existingOrder != null && existingOrder.getUser().equals(userId)) {
                    dataSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The cancel operation failed: " + databaseError.getCode());
            }
        });
    }
}