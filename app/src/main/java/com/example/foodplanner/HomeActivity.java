package com.example.foodplanner;

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

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bar);
        FirebaseApp.initializeApp(this);
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.homeScreenFragment || destination.getId() == R.id.searchFragment || destination.getId() == R.id.favoriteMealsFragment) {
                bottomNavigationView.setVisibility(View.VISIBLE);
            } else {
                bottomNavigationView.setVisibility(View.GONE);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int currentFragmentId = navController.getCurrentDestination().getId();
            if (item.getItemId() == R.id.home && currentFragmentId != R.id.homeScreenFragment) {
                navController.navigate(R.id.homeScreenFragment);
                return true;
            } else if (item.getItemId() == R.id.search && currentFragmentId != R.id.searchFragment) {
                navController.navigate(R.id.searchFragment);
                return true;
            } else if (item.getItemId() == R.id.favorites && currentFragmentId != R.id.favoriteMealsFragment) {
                navController.navigate(R.id.favoriteMealsFragment);
                return true;
            }
            return false;
        });

    }
}