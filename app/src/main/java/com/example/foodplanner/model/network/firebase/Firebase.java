package com.example.foodplanner.model.network.firebase;

import android.util.Log;
import androidx.annotation.NonNull;

import com.example.foodplanner.model.pojos.Meal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Firebase {
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    private static Firebase firebase = null;
    public static Firebase getInstance(){
        if(firebase == null){
            firebase = new Firebase();
        }
        return firebase;
    }
    private Firebase() {
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }
    public String getUserId() {
        FirebaseUser user = auth.getCurrentUser();
        return user != null ? user.getUid() : null;
    }
    public void createAccount(String email, String password, AuthCallback callback) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onComplete(email, password, null);
                    } else {
                        callback.onComplete("", "", task.getException().getMessage());
                    }
                });
    }
    public void signIn(String email, String password, AuthCallback callback) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onComplete(email, password, null);
                    } else {
                        callback.onComplete("", "", task.getException().getMessage());
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
    public boolean isGuest() {
        FirebaseUser user = auth.getCurrentUser();
        return user == null;
    }
    public void logOut() {
        auth.signOut();
    }
    public void addMealToFireStore(Meal meal) {
        String userId = getUserId();
        if (userId != null) {
            firestore.collection("users")
                    .document(userId)
                    .collection("meals")
                    .document(String.valueOf(meal.getUniqueId()))
                    .set(meal)
                    .addOnSuccessListener(aVoid -> Log.d("Firestore", "Meal added successfully"))
                    .addOnFailureListener(e -> Log.e("Firestore", "Error adding meal", e));
        } else {
            Log.e("Firestore", "User not logged in, can't add meal");
        }
    }
    public void deleteMealFromFireStore(String mealId){
        String userId = getUserId();
        if (userId != null) {
            firestore.collection("users")
                    .document(userId)
                    .collection("meals")
                    .document(mealId)
                    .delete()
                    .addOnSuccessListener(aVoid -> Log.d("Firestore", "Meal deleted successfully"))
                    .addOnFailureListener(e -> Log.e("Firestore", "Error deleting meal", e));
        } else {
            Log.e("Firestore", "User not logged in, cannot delete meal");
        }
    }
    public void restoreMealsFromFireStore(MealsRestoreCallback callback) {
        firestore.collection("users")
                .document(getUserId())
                .collection("meals")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Meal> meals = new ArrayList<>();
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        Meal meal = doc.toObject(Meal.class);
                        if (meal != null) {
                            meals.add(meal);
                            Log.i("TAG", "Meal fetched: " + meal.getMealName());
                        }
                    }
                    callback.onMealsRestored(meals);
                })
                .addOnFailureListener(e -> {
                    callback.onFailure("Error fetching meals: " + e.getMessage());
                });
    }
}
