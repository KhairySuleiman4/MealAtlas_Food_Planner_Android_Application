package com.example.foodplanner.model.network.meal;

import com.example.foodplanner.model.pojos.MealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("random.php")
    Single<MealResponse> getRandomMeal();
    @GET("list.php?i=list")
    Single<MealResponse> getAllIngredients();
    @GET("filter.php")
    Single<MealResponse> getMealsByCategory(@Query("c") String category);
    @GET("filter.php")
    Single<MealResponse> getMealsByCountry(@Query("a") String country);
    @GET("filter.php")
    Single<MealResponse> getMealsByIngredient(@Query("i") String ingredient);

}
