package com.example.foodplanner.screens.searchscreen.presenter;

import android.view.View;

public interface SearchPresenter {
    void getCategories();
    void getCountries();
    void getIngredients();
    void getMealsByCategory(String category, View v);
    void getMealsByCountry(String country, View v);
    void getMealsByIngredient(String ingredient, View v);
}
