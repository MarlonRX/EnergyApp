// RegisterActivity.java
package com.example.energyappjava.Views.userPages;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.ComponentActivity;

import com.example.energyappjava.ModelController.Actors.User;
import com.example.energyappjava.ModelController.UserController;
import com.example.energyappjava.R;

public class RegisterActivity extends ComponentActivity {

    private EditText usernameEdit;
    private EditText emailEditText;
    private EditText passwordEditText;
    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        usernameEdit = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditTextRegister);
        passwordEditText = findViewById(R.id.passwordEditTextRegister);

        Button registerButton = findViewById(R.id.registerButton);

        userController = new UserController(this);

        registerButton.setOnClickListener(v -> {
            fetchNewUserData();
        });

        Button backButton = findViewById(R.id.loginButton);
        backButton.setOnClickListener(v -> {
            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        });
    }

    public void fetchNewUserData() {
        String email = emailEditText.getText().toString();
        String username = usernameEdit.getText().toString();
        String password = passwordEditText.getText().toString();
        User newUser = new User(email, password, username);
        userController.registerUser(newUser);
    }
}