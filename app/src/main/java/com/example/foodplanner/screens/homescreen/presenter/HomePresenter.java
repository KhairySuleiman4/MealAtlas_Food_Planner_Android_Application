package com.example.foodplanner.screens.homescreen.presenter;

import android.view.View;

import com.example.foodplanner.model.pojos.Country;

import java.util.List;

public interface HomePresenter {
    void getCategories();
    void getRandomMeal();
    void getCountries();
    void getIngredients();
    void getMealsByCategory(String category, View v);
    void getMealsByCountry(String country, View v);
    void getMealsByIngredient(String ingredient, View v);
    void logout(View v);
    boolean isGuest();
}
