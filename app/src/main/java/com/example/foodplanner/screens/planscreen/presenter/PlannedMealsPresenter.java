package com.example.foodplanner.screens.planscreen.presenter;

import com.example.foodplanner.model.pojos.Meal;

public interface PlannedMealsPresenter {
    void getPlannedMeals(String day);
    void deleteMeal(Meal meal);
    void closeDisposable();
}
