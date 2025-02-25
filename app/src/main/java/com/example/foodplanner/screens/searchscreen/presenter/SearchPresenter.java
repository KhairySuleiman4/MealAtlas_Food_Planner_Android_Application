package com.example.foodplanner.screens.searchscreen.presenter;

import android.view.View;
import android.widget.EditText;

import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.Country;

import java.util.List;

public interface SearchPresenter {
    void getCategories();
    void getCountries();
    void getIngredients();
    void getMealsByCategory(String category, View v);
    void getMealsByCountry(String country, View v);
    void getMealsByIngredient(String ingredient, View v);
    void observeCategorySearch(EditText etSearch, List<Category> categories);
    void observeCountrySearch(EditText etSearch, List<Country> countries);
    void observeIngredientSearch(EditText etSearch, List<String> ingredients);
    void closeDisposable();
}
