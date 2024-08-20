package com.example.energyappjava.ModelController;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class MeterController {

    private final Context context;

    public MeterController(Context context) {
        this.context = context;
    }

    public void showModal() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Título del modal"); // Título del modal
        builder.setMessage("Mensaje del modal"); // Mensaje del modal
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción al hacer clic en el botón "Aceptar"
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción al hacer clic en el botón "Cancelar"
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}