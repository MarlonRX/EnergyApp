package com.example.energyappjava.Views;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import com.example.energyappjava.R;

public class ConsumeActivity extends ComponentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consume);

        Button StoreButton = findViewById(R.id.store_button);
        Button MeasureButton = findViewById(R.id.measure_button);
        Button monthlyButton = findViewById(R.id.monthly_button);
        Button hourlyButton = findViewById(R.id.hourly_button);
        Button dailyButton = findViewById(R.id.daily_button);
        Button weeklyButton = findViewById(R.id.weekly_button);

        StoreButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, StoreActivity.class);
            startActivity(intent);
        });

        MeasureButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, IndexActivity.class);
            startActivity(intent);
        });

        monthlyButton.setOnClickListener(v -> showDialog("Consumo por hora"));
        hourlyButton.setOnClickListener(v -> showDialog("Consumo por dia"));
        dailyButton.setOnClickListener(v -> showDialog("Consumo por semana"));
        weeklyButton.setOnClickListener(v -> showDialog("Consumo por mes"));
    }

    private void showDialog(String message) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);
        TextView textView = dialog.findViewById(R.id.dialog_text);
        textView.setText(message);
        dialog.show();
    }
}