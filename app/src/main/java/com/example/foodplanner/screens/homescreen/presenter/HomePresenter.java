package com.example.foodplanner.screens.homescreen.presenter;

import com.example.foodplanner.model.pojos.Country;

import java.util.List;

public interface HomePresenter {
    void getCategories();
    void getRandomMeal();
    void getCountries();
    void getIngredients();
}
