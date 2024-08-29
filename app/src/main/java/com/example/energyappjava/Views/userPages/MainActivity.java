// MainActivity.java
package com.example.energyappjava.Views.userPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.ComponentActivity;

import com.example.energyappjava.ModelController.UserController;
import com.example.energyappjava.ModelController.Actors.User;
import com.example.energyappjava.R;

public class MainActivity extends ComponentActivity {

    private UserController userController;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        userController = new UserController(this);

        progressBar = findViewById(R.id.progressBar);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            authUser();
        });

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(v -> {
            goToRegisterActivity();
        });
    }

    public void goToRegisterActivity() {
        Intent i = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(i);
        finish();
    }

    public void authUser() {
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        User existentUser = new User(email, password);
        showProgressBar();
        userController.loginUser(existentUser);
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}