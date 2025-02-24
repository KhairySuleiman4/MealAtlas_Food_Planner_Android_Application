package com.example.foodplanner.screens.signupscreen.presenter;

import com.example.foodplanner.model.network.firebase.AuthCallback;
import com.example.foodplanner.model.network.firebase.Firebase;
import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.screens.signupscreen.view.SignUpView;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpPresenterImp implements SignUpPresenter{
    SignUpView view;
    MealsRepositoryImp repo;
    public SignUpPresenterImp(SignUpView view, MealsRepositoryImp repo) {
        this.view = view;
        this.repo = repo;
    }
    @Override
    public void giveCredentials(String email, String password) {
        if(email.isBlank() && password.isBlank()){
            view.onFailure("Please, enter an email and a password!");
            return;
        }

        if(email.isBlank()){
            view.onFailure("Please, enter the email!");
            return;
        }

        if(password.isBlank()){
            view.onFailure("Please, enter the password!");
            return;
        }

        repo.createAccount(email, password, (createdEmail, createdPassword, errorMessage) -> {
            if (errorMessage == null) {
                view.onSuccess(createdEmail, createdPassword);
            } else {
                view.onFailure(errorMessage);
            }
        });
    }
}
