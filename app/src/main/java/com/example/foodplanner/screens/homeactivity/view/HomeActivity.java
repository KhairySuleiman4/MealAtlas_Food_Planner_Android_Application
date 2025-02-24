package com.example.foodplanner.screens.homeactivity.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.example.foodplanner.R;


import com.example.foodplanner.model.db.MealsLocalDataSourceImp;
import com.example.foodplanner.model.network.meal.MealsRemoteDataSourceImp;
import com.example.foodplanner.model.network.meal.MealsRepositoryImp;
import com.example.foodplanner.screens.homeactivity.presenter.HomeActivityPresenter;
import com.example.foodplanner.screens.homeactivity.presenter.HomeActivityPresenterImp;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements HomeActivityView {
    boolean isGuest;
    HomeActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bar);
        presenter = new HomeActivityPresenterImp(this,
                MealsRepositoryImp.getInstance(
                        MealsRemoteDataSourceImp.getInstance(),
                        MealsLocalDataSourceImp.getInstance(this)));
        FirebaseApp.initializeApp(this);
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
                int itemId = item.getItemId();
                isGuest = presenter.isGuest();
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