// User.java
package com.example.energyappjava.ModelController.Actors;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String name;
    private String email;
    private String password;
    private String deviceId;

    // User for registration
    public User(String email, String password, String name, String deviceId) {
        this.email = email;
        this.password = hashPassword(password);
        this.name = name;
        this.deviceId = deviceId;
    }

    // User for login
    public User(String email, String password) {
        this.email = email;
        this.password = hashPassword(password);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    // Method to hash the password
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}