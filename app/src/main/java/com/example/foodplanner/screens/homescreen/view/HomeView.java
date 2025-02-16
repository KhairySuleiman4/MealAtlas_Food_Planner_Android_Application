package com.example.foodplanner.screens.homescreen.view;

import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

public interface HomeView {
    void showAllCategories(List<Category> categories);
    void showRandomMeal(Meal meal);
    void showError(String error);
}
