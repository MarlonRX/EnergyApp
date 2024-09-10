// StoreActivity.java
package com.example.energyappjava.Views;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.energyappjava.ModelController.Actors.EnergyMeter;
import com.example.energyappjava.ModelController.Actors.Order;
import com.example.energyappjava.ModelController.Actors.User;
import com.example.energyappjava.ModelController.StoreController;
import com.example.energyappjava.ModelController.UserController;
import com.example.energyappjava.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreActivity extends AppCompatActivity {
    private StoreController storeController;
    private List<EnergyMeter> selectedItems;
    private Map<EnergyMeter, Button> deviceButtons;
    private TextView totalValue;
    private double totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);

        TableLayout devicesTable = findViewById(R.id.devices_table);
        storeController = new StoreController(this, devicesTable);
        selectedItems = new ArrayList<>();
        deviceButtons = new HashMap<>();

        Button indexButton = findViewById(R.id.measure_button);
        Button consumeButton = findViewById(R.id.consume_button);
        Button orderButton = findViewById(R.id.orderButton);

        Button myOrdersButton = findViewById(R.id.myOrdersButton);
        myOrdersButton.setOnClickListener(v -> storeController.fetchOrders(this::openMyOrdersDialog));

        totalValue = findViewById(R.id.totalValue);

        indexButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, IndexActivity.class);
            startActivity(intent);
        });

        consumeButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ConsumeActivity.class);
            startActivity(intent);
        });

        orderButton.setOnClickListener(v -> showOrderConfirmationDialog());

        storeController.fetchEnergyMeters(this::populateTable);
        updateTotalValue();
    }

    private void populateTable(List<EnergyMeter> energyMeters) {
        TableLayout table = findViewById(R.id.devices_table);
        table.removeAllViews();

        TableRow headerRow = (TableRow) getLayoutInflater().inflate(R.layout.table_row_header, null);
        table.addView(headerRow);

        for (EnergyMeter meter : energyMeters) {
            TableRow row = (TableRow) getLayoutInflater().inflate(R.layout.table_row_item, null);

            TextView nameView = row.findViewById(R.id.device_name);
            nameView.setText(meter.getName());

            TextView typeView = row.findViewById(R.id.device_type);
            typeView.setText(meter.getType());

            TextView priceView = row.findViewById(R.id.device_price);
            priceView.setText(String.valueOf(meter.getPrice()));

            Button actionButton = row.findViewById(R.id.action_button);
            deviceButtons.put(meter, actionButton);
            actionButton.setOnClickListener(v -> {
                actionButton.setVisibility(Button.GONE);
                selectedItems.add(meter);
                populateOrderTable();
                updateTotalValue();
            });
            table.addView(row);
        }
    }

    private void populateOrderTable() {
        TableLayout orderTable = findViewById(R.id.order_table);
        orderTable.removeAllViews();

        TableRow headerRow = (TableRow) getLayoutInflater().inflate(R.layout.order_table_row_header, null);
        orderTable.addView(headerRow);

        for (EnergyMeter meter : selectedItems) {
            TableRow row = (TableRow) getLayoutInflater().inflate(R.layout.order_table_row_item, null);

            TextView nameView = row.findViewById(R.id.device_name);
            nameView.setText(meter.getName());

            TextView typeView = row.findViewById(R.id.device_type);
            typeView.setText(meter.getType());

            TextView priceView = row.findViewById(R.id.device_price);
            priceView.setText(String.valueOf(meter.getPrice()));

            Button orderButton = row.findViewById(R.id.orderButton);
            if (orderButton != null) {
                orderButton.setOnClickListener(v -> showOrderConfirmationDialog());
            }

            Button actionButton = row.findViewById(R.id.action_button);
            if (actionButton != null) {
                actionButton.setOnClickListener(v -> {
                    selectedItems.remove(meter);
                    Button deviceButton = deviceButtons.get(meter);
                    if (deviceButton != null) {
                        deviceButton.setVisibility(Button.VISIBLE);
                    }
                    populateOrderTable();
                    updateTotalValue();
                });
            }

            orderTable.addView(row);
        }
    }

    private void showOrderConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_order_confirmation, null);
        builder.setView(dialogView);

        EditText customerAddress = dialogView.findViewById(R.id.customer_address);
        EditText customerPhone = dialogView.findViewById(R.id.customer_phone);
        EditText customerDetail = dialogView.findViewById(R.id.details);
        TextView priceView = dialogView.findViewById(R.id.price);
        Button confirmButton = dialogView.findViewById(R.id.confirm_button);

        // Update the price TextView in the dialog with the total value
        priceView.setText(String.format("%.2f", totalAmount));

        AlertDialog dialog = builder.create();

        confirmButton.setOnClickListener(v -> {
            fetchOrderData(customerAddress, customerPhone, customerDetail, dialog);
        });

        dialog.show();
    }

    private void openMyOrdersDialog(List<Order> orders) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.my_orders_dialog, null);
        builder.setView(dialogView);

        renderOrderTable(dialogView, orders);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void renderOrderTable(View dialogView, List<Order> orders) {
        TableLayout orderTable = dialogView.findViewById(R.id.my_orders_table);
        orderTable.removeAllViews();

        TableRow headerRow = (TableRow) getLayoutInflater().inflate(R.layout.my_order_table_row_header, null);
        orderTable.addView(headerRow);

        for (Order order : orders) {
            TableRow row = (TableRow) getLayoutInflater().inflate(R.layout.order_table_row_item, null);

            TextView nameView = row.findViewById(R.id.device_name);
            nameView.setText(order.getValue().toString());

            TextView typeView = row.findViewById(R.id.device_type);
            typeView.setText(order.getDetail());

            TextView priceView = row.findViewById(R.id.device_price);
            priceView.setText(String.valueOf(order.getAddress()));

            Button cancelButton = row.findViewById(R.id.cancelButton);
            if (cancelButton != null) {
                cancelButton.setOnClickListener(v -> showOrderConfirmationDialog());
            }

            orderTable.addView(row);
        }
    }

    private void fetchOrderData(EditText customerAddress, EditText customerPhone, EditText customerDetail, AlertDialog dialog) {
        String address = customerAddress.getText().toString();
        String phone = customerPhone.getText().toString();
        String detail = customerDetail.getText().toString();

        UserController userController = new UserController(this);
        User user = userController.getUser();

        if (totalAmount == 0) {
            Toast.makeText(this, "Hace falta seleccionar un producto para hacer un pedido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!address.isEmpty() || !phone.isEmpty()) {
            Order order = new Order(user, selectedItems, totalAmount, detail, address, phone);
            storeController.createOrder(order, new StoreController.Callback<Void>() {
                @Override
                public void onSuccess(Void result) {
                    Toast.makeText(StoreActivity.this, "Registro de pedido exitoso", Toast.LENGTH_SHORT).show();
                    selectedItems.clear();
                    populateOrderTable();
                    updateTotalValue();
                    dialog.dismiss();
                    goToIndexActivity();
                }

                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(StoreActivity.this, "Error al registrar el pedido", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Show error message
            Toast.makeText(this, "Diligencie correctamente el formulario por favor", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToIndexActivity() {
        Intent intent = new Intent(StoreActivity.this, IndexActivity.class);
        startActivity(intent);
    }

    private void updateTotalValue() {
        totalAmount = 0.0;
        for (EnergyMeter meter : selectedItems) {
            totalAmount += meter.getPrice();
        }
        totalValue.setText(String.format("%.2f", totalAmount));
    }

    public List<EnergyMeter> getSelectedItems() {
        return selectedItems;
    }
}