package com.example.foodplanner.screens.planscreen.view;

import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

public interface PlanScreenView {
    void showPlannedMeals(List<Meal> meals);
    void showDeleted(String message);
    void showError(String error);
}
