package com.example.energyappjava.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.ComponentActivity;

import com.example.energyappjava.R;

public class ConsumeActivity extends ComponentActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consume);

        Button ModalButton = findViewById(R.id.menu_button);
        Button StoreButton = findViewById(R.id.store_button);
        Button MeasureButton = findViewById(R.id.measure_button);

        StoreButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, StoreActivity.class);
            startActivity(intent);
        });

        MeasureButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, IndexActivity.class);
            startActivity(intent);
        });
    }
}
