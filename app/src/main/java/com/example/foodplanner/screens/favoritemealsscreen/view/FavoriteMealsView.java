package com.example.foodplanner.screens.favoritemealsscreen.view;

import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

public interface FavoriteMealsView {
    void showFavMeals(List<Meal> meals);
    void showDeleted(String message);
}
