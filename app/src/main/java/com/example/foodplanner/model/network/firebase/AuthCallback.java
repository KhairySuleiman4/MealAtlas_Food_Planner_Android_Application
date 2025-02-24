package com.example.foodplanner.model.network.firebase;

public interface AuthCallback {
    void onComplete(String email, String password, String errorMessage);
}
