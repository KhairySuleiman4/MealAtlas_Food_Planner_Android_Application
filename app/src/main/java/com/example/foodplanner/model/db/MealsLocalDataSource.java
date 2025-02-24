package com.example.foodplanner.model.db;

import androidx.room.Query;

import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface MealsLocalDataSource {
    Observable<List<Meal>> getFavMeals();
    Completable insertFavMeal(Meal meal);
    Completable deleteFavMeal(Meal meal);
    Single<Boolean> isMealFavorite(long mealId);
    Completable insertPlannedMeal(Meal meal);
    Completable deletePlannedMeal(Meal meal);
    Observable<List<Meal>> getPlannedMeals(String date);
    Single<Meal> getLastMeal();

}
