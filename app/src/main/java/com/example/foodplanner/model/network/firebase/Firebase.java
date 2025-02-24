package com.example.foodplanner.model.network.firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

public class Firebase {
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    public Firebase() {
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    public String getUserId() {
        if (auth.getCurrentUser() != null)
            return auth.getCurrentUser().getUid();
        else
            return null;
    }

    public void createAccount(String email, String password, AuthCallback callback) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            callback.onComplete(email, password, null);
                        } else {
                            callback.onComplete("", "", task.getException().getMessage());
                        }
                    }
                });
    }

    public void signIn(String email, String password, AuthCallback callback) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            callback.onComplete(email, password, null);
                        } else {
                            callback.onComplete("", "", task.getException().getMessage());
                        }
                    }
                });
    }

    public void handleGoogleSignIn(String idToken, AuthCallback callback) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    callback.onComplete(user.getEmail(), "Google Account", null);
                }
            } else {
                callback.onComplete("", "", "Google Sign-In failed!");
            }
        });
    }
}
