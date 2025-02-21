package com.example.foodplanner.screens.splashscreen.presenter;

import android.util.Log;

import com.example.foodplanner.screens.splashscreen.view.SplashView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashPresenterImp implements SplashPresenter{
    SplashView view;
    FirebaseAuth auth;

    public SplashPresenterImp(SplashView view) {
        this.view = view;
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void checkUserAuthentication() {
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            view.navigateToHome();
        } else{
            view.navigateToWelcome();
        }
    }
}
