package com.example.foodplanner.model.network.meal;

import com.example.foodplanner.model.pojos.Category;
import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface MealsRepository {
    Single<Meal> mealNetworkCall();
    Single<List<Meal>> ingredientsNetworkCall();
    Single<List<Meal>> mealsByCategory(String category);
    Single<List<Meal>> mealsByCountry(String country);
    Single<List<Meal>> mealsByIngredient(String ingredient);
    Single<Meal> mealByName(String mealName);
    Observable<List<Meal>> getFavMeals();
    Completable insertMeal(Meal meal);
    Completable deleteMeal(Meal meal);
    Single<Boolean> doesMealExist(long mealId);
}
