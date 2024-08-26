// UserController.java
package com.example.energyappjava.ModelController;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import com.example.energyappjava.ModelController.Actors.User;
import com.example.energyappjava.Views.IndexActivity;
import com.example.energyappjava.Views.userPages.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class UserController {

    private final Context context;
    private final FirebaseAuth auth;
    private final DatabaseReference database;
    private static final String TAG = "UserController";

    public UserController(Context context) {
        this.context = context;
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance().getReference();
    }

    public void loginUser(User user) {
        if (user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            Toast.makeText(context, "Por favor, ingresa tu email y contraseña", Toast.LENGTH_SHORT).show();
            ((MainActivity) context).hideProgressBar();
        } else {
            try {
                auth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                        .addOnCompleteListener(task -> {
                            ((MainActivity) context).hideProgressBar();
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(context, IndexActivity.class);
                                context.startActivity(intent);
                            } else {
                                handleLoginFailure(task.getException());
                            }
                        });
            } catch (Exception e) {
                Log.e(TAG, "Unexpected error during login", e);
                Toast.makeText(context, "Error inesperado durante el inicio de sesión", Toast.LENGTH_SHORT).show();
                ((MainActivity) context).hideProgressBar();
            }
        }
    }

    private void handleLoginFailure(Exception exception) {
        if (exception instanceof FirebaseAuthInvalidUserException) {
            Toast.makeText(context, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
        } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
            Toast.makeText(context, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
        } else {
            Log.e(TAG, "Login failed", exception);
            Toast.makeText(context, "Error en el inicio de sesión: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public User getUser() {
        return new User(Objects.requireNonNull(auth.getCurrentUser()).getEmail(), "", "");
    }

    public void registerUser(User user) {
        if (user.getEmail().isEmpty() || user.getPassword().isEmpty() || user.getName().isEmpty()) {
            Toast.makeText(context, "Por favor, ingresa tu email, contraseña y nombre de usuario", Toast.LENGTH_SHORT).show();
        } else {
            auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                    .addOnSuccessListener(authResult -> {
                        String userId = Objects.requireNonNull(authResult.getUser()).getUid();
                        database.child("users").child(userId).setValue(user)
                                .addOnSuccessListener(aVoid -> {
                                    Intent intent = new Intent(context, IndexActivity.class);
                                    context.startActivity(intent);
                                    ((ComponentActivity) context).finish();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(context, "Error al registrar el usuario: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(context, "Error en el registro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }
    }
}