package com.example.foodplanner.screens.signupscreen.presenter;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.example.foodplanner.screens.signupscreen.view.SignUpView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpPresenterImp implements SignUpPresenter{
    SignUpView view;
    FirebaseAuth auth;
    public SignUpPresenterImp(SignUpView view) {
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

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            view.onSuccess(email, password);
                        } else {
                            view.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }
}
