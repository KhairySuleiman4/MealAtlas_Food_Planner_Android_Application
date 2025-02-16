package com.example.foodplanner.model.network.meal;

import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

public interface MealNetworkCallBack {
    void onSuccessRandomMealResult(List<Meal> products);
    void onFailRandomMealResult(String error);
}
