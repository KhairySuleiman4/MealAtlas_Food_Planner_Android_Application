package com.example.foodplanner;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.example.foodplanner.R;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth auth;
    boolean isGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bar);
        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.homeScreenFragment || destination.getId() == R.id.searchFragment || destination.getId() == R.id.favoriteMealsFragment || destination.getId() == R.id.planScreenFragment || destination.getId() == R.id.needToRegisterFragment) {
                bottomNavigationView.setVisibility(View.VISIBLE);
            } else {
                bottomNavigationView.setVisibility(View.GONE);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(auth.getCurrentUser() == null)
                    isGuest = true;
                else isGuest = false;
                int itemId = item.getItemId();
                if (itemId == R.id.homeScreenFragment || itemId == R.id.searchFragment) {
                    navController.navigate(itemId);
                    return true;
                }
                if (isGuest) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                    builder.setMessage("You need to register to use this feature :)");
                    builder.setTitle("Register First!");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ok", (dialog, which) -> {
                        navController.navigate(R.id.welcomeFragment);
                    });
                    builder.setNegativeButton("Cancel", (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    return false;
                }
                navController.navigate(itemId);
                return true;
            }
        });

    }
}