package com.example.energyappjava.Views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import com.example.energyappjava.ModelController.MeterController;
import com.example.energyappjava.R;

import java.util.Random;

public class IndexActivity extends ComponentActivity {

    private MeterController indexController;
    private TextView kwValueTextView;
    private ProgressBar progressBar;
    private Handler handler;
    private Runnable updateKWValueRunnable;
    private boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        indexController = new MeterController(this, "DireccionDispositivoBluetooth");

        Button startButton = findViewById(R.id.start_button);
        Button storeButton = findViewById(R.id.store_button);
        Button consumeButton = findViewById(R.id.consume_button);
        kwValueTextView = findViewById(R.id.kw_value);
        progressBar = findViewById(R.id.progress_bar);
        handler = new Handler(Looper.getMainLooper());

        updateKWValueRunnable = new Runnable() {
            @Override
            public void run() {
                double randomKW = generateRandomKW();
                kwValueTextView.setText(String.format("%.2f kW", randomKW));
                handler.postDelayed(this, 1000);
            }
        };

        startButton.setOnClickListener(v -> {
            if (isUpdating) {
                stopUpdatingKWValue();
            } else {
                startUpdatingKWValue();
            }
        });

        storeButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, StoreActivity.class);
            startActivity(intent);
        });

        consumeButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ConsumeActivity.class);
            startActivity(intent);
        });
    }

    private void startUpdatingKWValue() {
        isUpdating = true;
        progressBar.setVisibility(ProgressBar.VISIBLE);
        handler.post(updateKWValueRunnable);
    }

    private void stopUpdatingKWValue() {
        isUpdating = false;
        progressBar.setVisibility(ProgressBar.GONE);
        handler.removeCallbacks(updateKWValueRunnable);
        kwValueTextView.setText("INICIAR");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopUpdatingKWValue();
    }

    private double generateRandomKW() {
        Random random = new Random();
        return 0.2 + (2.2 - 0.2) * random.nextDouble();
    }
}