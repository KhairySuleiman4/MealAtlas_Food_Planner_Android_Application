package com.example.foodplanner.model.network.meal;

import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

public interface MealNetworkCallBack {
    public void onSuccessResult(List<Meal> products);
    public void onFailResult(String error);
}
