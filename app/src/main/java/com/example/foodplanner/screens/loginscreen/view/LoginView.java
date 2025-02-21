package com.example.foodplanner.screens.loginscreen.view;

public interface LoginView {
    void onSuccess(String email, String password);
    void onFailure(String message);
}
