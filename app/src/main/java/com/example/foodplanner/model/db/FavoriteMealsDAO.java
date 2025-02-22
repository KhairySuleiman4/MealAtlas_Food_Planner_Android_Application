package com.example.foodplanner.model.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.pojos.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface FavoriteMealsDAO {
    @Query("Select * from favorite_meals")
    Observable<List<Meal>> getFavMeals();
    //Remember to delete the conflict strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMeal(Meal meal);
    @Delete
    Completable deleteMeal(Meal meal);
    @Query("Select exists(Select 1 from favorite_meals where idMeal = :mealId)")
    Single<Boolean> doesMealExist(long mealId);
}
