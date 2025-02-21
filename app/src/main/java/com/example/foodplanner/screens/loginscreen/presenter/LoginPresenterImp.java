package com.example.foodplanner.screens.loginscreen.presenter;

import androidx.annotation.NonNull;

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
    FirebaseAuth auth;
    public LoginPresenterImp(LoginView view) {
        this.view = view;
        auth = FirebaseAuth.getInstance();
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

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = auth.getCurrentUser();
                            view.onSuccess(email, password);
                        } else {
                            view.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }

    public void handleGoogleSignIn(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    view.onSuccess(user.getEmail(), "Google Account");
                }
            } else {
                view.onFailure("Google Sign-In failed!");
            }
        });
    }
}
