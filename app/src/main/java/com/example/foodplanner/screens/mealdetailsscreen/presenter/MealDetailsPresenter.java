package com.example.foodplanner.screens.mealdetailsscreen.presenter;

import com.example.foodplanner.model.pojos.Meal;

public interface MealDetailsPresenter {
    void insertMeal(Meal meal);
    void deleteMealFromFavorite(Meal meal);
    void checkIsFavorite(long mealId);
    void insertPlannedMeal(Meal meal);
}
