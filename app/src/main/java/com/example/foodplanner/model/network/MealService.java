package com.example.foodplanner.model.network;

import com.example.foodplanner.model.pojos.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {
    @GET("random.php")
    Call<MealResponse> getRandomMeal();
}
