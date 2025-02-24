package com.example.foodplanner.model.network.firebase;

import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

public interface MealsRestoreCallback {
    void onMealsRestored(List<Meal> meals);
    void onFailure(String errorMessage);
}
