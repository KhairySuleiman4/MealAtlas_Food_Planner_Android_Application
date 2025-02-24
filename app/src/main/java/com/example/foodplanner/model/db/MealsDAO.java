package com.example.foodplanner.model.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealsDAO {
    @Query("Select * from meals where isFavorite = 1")
    Observable<List<Meal>> getFavMeals();
    @Insert
    Completable insertFavMeal(Meal meal);
    @Delete
    Completable deleteFavMeal(Meal meal);
    @Query("Select exists(Select 1 from meals where idMeal = :mealId and isFavorite = 1)")
    Single<Boolean> isMealFavorite(long mealId);
    @Insert
    Completable insertPlannedMeal(Meal meal);
    @Delete
    Completable deletePlannedMeal(Meal meal);
    @Query("Select * from meals where date = :date")
    Observable<List<Meal>> getPlannedMeals(String date);
    @Query("SELECT * FROM meals ORDER BY uniqueId DESC LIMIT 1")
    Single<Meal> getLastMeal();
    @Query("DELETE from meals")
    Completable clearAllMeals();
}
