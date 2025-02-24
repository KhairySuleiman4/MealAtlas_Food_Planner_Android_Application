package com.example.foodplanner.screens.loginscreen.presenter;

import androidx.annotation.NonNull;

import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.screens.loginscreen.view.LoginView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginPresenterImp implements LoginPresenter{
    LoginView view;
    MealsRepositoryImp repo;
    public LoginPresenterImp(LoginView view, MealsRepositoryImp repo) {
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

        repo.signIn(email, password, (createdEmail, createdPassword, errorMessage) -> {
            if (errorMessage == null) {
                view.onSuccess(createdEmail, createdPassword);
            } else {
                view.onFailure(errorMessage);
            }
        });
    }
    public void handleGoogleSignIn(String idToken) {
        repo.handleGoogleSignIn(idToken, (email, password, errorMessage) -> {
            if (errorMessage == null) {
                view.onSuccess(email, "Google Account");
            } else {
                view.onFailure(errorMessage);
            }
        });
    }
}
