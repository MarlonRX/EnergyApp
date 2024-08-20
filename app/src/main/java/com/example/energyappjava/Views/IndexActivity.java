package com.example.energyappjava.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.ComponentActivity;

import com.example.energyappjava.ModelController.MeterController;
import com.example.energyappjava.R;

public class IndexActivity extends ComponentActivity {

    private MeterController indexController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        indexController = new MeterController(this);

        Button ModalButton = findViewById(R.id.menu_button);
        Button StoreButton = findViewById(R.id.store_button);
        Button ConsumeButton = findViewById(R.id.consume_button);

        ModalButton.setOnClickListener(v -> indexController.showModal());

        StoreButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, StoreActivity.class);
            startActivity(intent);
        });

        ConsumeButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ConsumeActivity.class);
            startActivity(intent);
        });
    }
}