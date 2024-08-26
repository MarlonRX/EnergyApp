// StoreController.java
package com.example.energyappjava.ModelController;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;

import android.widget.TableLayout;

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

    public void fetchEnergyMeters(FetchEnergyMetersCallback callback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Devices");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<EnergyMeter> energyMeters = new ArrayList<>();
                for (DataSnapshot meterSnapshot : dataSnapshot.getChildren()) {
                    String name = meterSnapshot.child("Name").getValue(String.class);
                    String type = meterSnapshot.child("Type").getValue(String.class);
                    double price = meterSnapshot.child("Price").getValue(double.class);

                    EnergyMeter energyMeter = new EnergyMeter(name, type, price);
                    energyMeters.add(energyMeter);
                }
                callback.onFetchEnergyMeters(energyMeters);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public interface FetchEnergyMetersCallback {
        void onFetchEnergyMeters(List<EnergyMeter> energyMeters);
    }

    public void createOrder(Order order, Callback<Void> callback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Orders");
        databaseReference.push().setValue(order)
            .addOnSuccessListener(aVoid -> callback.onSuccess(null))
            .addOnFailureListener(callback::onFailure);
    }

    public interface Callback<T> {
        void onSuccess(T result);
        void onFailure(Exception e);
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