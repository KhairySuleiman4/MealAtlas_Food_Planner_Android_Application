package com.example.foodplanner.screens.mealdetailsscreen.view;

public interface MealDetailsView {
    void showFeedback(String message);
    void mealIsFavorite();
    void mealIsNotFavorite();
    void showError(String error);
}
