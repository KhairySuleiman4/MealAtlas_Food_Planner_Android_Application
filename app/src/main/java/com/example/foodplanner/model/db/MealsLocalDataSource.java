package com.example.foodplanner.model.db;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public interface MealsLocalDataSource {
    Observable<List<Meal>> getFavMeals();
    Completable insertMeal(Meal meal);
    Completable deleteMeal(Meal meal);
}
