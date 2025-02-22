package com.example.foodplanner.screens.planscreen.view;

import com.example.foodplanner.model.pojos.Meal;

public interface OnPlannedMealClickListener {
    void onRemoveClick(Meal meal);
    void onImageClick(Meal meal);
}
