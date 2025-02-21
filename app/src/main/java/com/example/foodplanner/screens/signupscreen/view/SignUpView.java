package com.example.foodplanner.screens.signupscreen.view;

public interface SignUpView {
    void onSuccess(String email, String password);
    void onFailure(String message);
}
