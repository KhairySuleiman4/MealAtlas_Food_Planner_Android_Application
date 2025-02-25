package com.example.foodplanner.model.network.meal;

import com.example.foodplanner.model.pojos.Meal;
import com.example.foodplanner.model.pojos.MealResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface MealsRemoteDataSource {
    Single<Meal> mealNetworkCall();
    Single<List<Meal>> ingredientsNetworkCall();
    Single<List<Meal>> mealsByCategory(String category);
    Single<List<Meal>> mealsByCountry(String country);
    Single<List<Meal>> mealsByIngredient(String ingredient);
    Single<Meal> getMealById(long mealId);
}
